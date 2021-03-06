package com.lihebin.market.service.impl;

import com.lihebin.market.bean.Code;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.params.FileRes;
import com.lihebin.market.service.FileService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

/**
 * Created by lihebin on 2019/4/4.
 */
@Service
public class FileServiceImpl implements FileService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Value("${url.picture}")
    private String pictureUrl;


    @Value("${url.login.username}")
    private String loginUsername;

    @Value("${url.login.password}")
    private String loginPassword;

    private static final String CODE = "code";
    private static final String CODE_OK = "OK";
    private static final String DATA = "data";
    private static final String DATA_UUID = "uuid";
    private static final String DATA_NAME = "name";


    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EXPIRE_TIME = "expireTime";
    private static final String PRIVACY = "privacy";
    private static final String SIZE = "size";
    private static final String DIR = "dir";
    private static final String FILENAME = "filename";

    private static final String UPLOAD_USER_UUID = "userUuid";
    private static final String UPLOAD_ALIEN = "alien";
    private static final String UPLOAD_PUUID = "puuid";
    private static final String UPLOAD_FILE = "file";

    private static final String DIR_DEFAULT = "/admin";

    private static final Boolean UPLOAD_ALIEN_DEFAULT = false;
    private static final String UPLOAD_PUUID_DEFAULT = "root";

    @Autowired
    private RestTemplate restTemplate;




    @Override
    public FileRes uploadPicture(MultipartFile file) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(USERNAME, loginUsername);
        map.add(PASSWORD, loginPassword);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(map, headers);
        ResponseEntity<Map> loginResult = restTemplate.postForEntity(String.format("%s/api/user/login", pictureUrl), formEntity, Map.class);
        String cookie = loginResult.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        String code = MapUtils.getString(loginResult.getBody(), CODE);
        if (!CODE_OK.equals(code)) {
            log.error("uploadPicture: {},{}", "登录失败", loginResult);
            throw new BackendException(Code.CODE_PARAM_ERROR, "上传文件失败");
        }
        String uuid = MapUtils.getString(MapUtils.getMap(loginResult.getBody(), DATA), DATA_UUID);
        File tempFile = multipartFileToFile(file);
        if (null == tempFile) {
            log.error("uploadPicture: {},{}", "转换文件失败", file.getName());
            throw new BackendException(Code.CODE_PARAM_ERROR, "上传文件失败");
        }
        FileSystemResource resource = new FileSystemResource(tempFile);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add(UPLOAD_FILE, resource);
        param.add(UPLOAD_USER_UUID, uuid);
        param.add(UPLOAD_PUUID, UPLOAD_PUUID_DEFAULT);
        param.add(UPLOAD_ALIEN, UPLOAD_ALIEN_DEFAULT);
        HttpHeaders headersUpload = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headersUpload.add(HttpHeaders.COOKIE, cookie);
        HttpEntity<MultiValueMap<String, Object>> uploadEntity = new HttpEntity<>(param, headersUpload);
        Map uploadResult = restTemplate.postForObject(String.format("%s/api/matter/upload", pictureUrl), uploadEntity, Map.class);
        code = MapUtils.getString(uploadResult, CODE);
        if (!CODE_OK.equals(code)) {
            log.error("uploadPicture: {},{}", "上传文件失败", uploadResult);
            throw new BackendException(Code.CODE_PARAM_ERROR, "上传文件失败");
        }
        uuid = MapUtils.getString(MapUtils.getMap(uploadResult, DATA), DATA_UUID);
        String name = MapUtils.getString(MapUtils.getMap(uploadResult, DATA), DATA_NAME);
        FileRes fileRes = new FileRes();
        fileRes.setUrl(String.format("%s/api/alien/download/%s/%s", pictureUrl, uuid, name));
        return fileRes;
    }

    @Override
    public FileRes uploadPictureToken(String filename, String size) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(USERNAME, loginUsername);
        map.add(EXPIRE_TIME, loginPassword);
        map.add(FILENAME, filename);
        map.add(PRIVACY, "false");
        map.add(SIZE, size);
        map.add(DIR, DIR_DEFAULT);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(map, headers);
        Map tokenResult = restTemplate.postForObject(String.format("%s/api/alien/fetch/upload/token", pictureUrl), formEntity, Map.class);
        String code = MapUtils.getString(tokenResult, CODE);
        if (!CODE_OK.equalsIgnoreCase(code)) {
            log.error("uploadPictureToken: {},{}", "获取token失败", tokenResult);
            throw new BackendException(Code.CODE_PARAM_ERROR, "获取token失败");
        }
        String uuid = MapUtils.getString(MapUtils.getMap(tokenResult, DATA), DATA_UUID);
        FileRes fileRes = new FileRes();
        fileRes.setToken(uuid);
        return fileRes;
    }


    private static File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 用当前时间作为文件名，防止生成的临时文件重复
        try {
            File file = File.createTempFile(System.currentTimeMillis() + "", prefix);

            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
//            log.error("multipartFileToFile: {}", fileName, e);
        }
        return null;
    }

    public static void main(String[] args) {
        RestTemplate test = new RestTemplate();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "root");
        map.add("password", "include");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(map, headers);
        ResponseEntity<Map> loginResult = test.postForEntity("http://129.28.192.214:6010/api/user/login", formEntity, Map.class);
        String cookie = loginResult.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        String code = MapUtils.getString(loginResult.getBody(), CODE);
        if (!CODE_OK.equalsIgnoreCase(code)) {
            return;
        }
        String uuid = MapUtils.getString(MapUtils.getMap(loginResult.getBody(), DATA), DATA_UUID);
        FileSystemResource resource = new FileSystemResource(new File("/Users/lihebin/Desktop/bg.jpg"));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add(UPLOAD_FILE, resource);
        param.add(UPLOAD_USER_UUID, uuid);
        param.add(UPLOAD_PUUID, UPLOAD_PUUID_DEFAULT);
        param.add(UPLOAD_ALIEN, UPLOAD_ALIEN_DEFAULT);
        HttpHeaders headersUpload = new HttpHeaders();
        headersUpload.setContentType(MediaType.MULTIPART_FORM_DATA);
        headersUpload.add(HttpHeaders.COOKIE, cookie);
//        headersUpload.setCacheControl("no-cache");
        HttpEntity<MultiValueMap<String, Object>> uploadEntity = new HttpEntity<>(param, headersUpload);
        Map upload = test.postForObject("http://129.28.192.214:6010/api/matter/upload", uploadEntity, Map.class);

        System.out.println(upload);
    }
}

package com.lihebin.market.wx.service.impl;

import com.lihebin.market.data.dao.StorageDao;
import com.lihebin.market.data.model.StorageData;
import com.lihebin.market.data.storage.Storage;
import com.lihebin.market.enums.CodeEnum;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.utils.StringUtil;
import com.lihebin.market.wx.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/4/1 10:18 下午
 */
@Slf4j
@Service
public class StorageServiceImpl implements StorageService {


    @Value("${market.storage.active}")
    private String active;

    @Autowired
    private StorageDao storageDao;

    @Autowired
    private Map<String, Storage> storageMap;

    private Storage storage;



    @PostConstruct
    public void initStorage() {
        this.storage = storageMap.get(active);
    }

    @Override
    public Page<StorageData> list(String key, String name, int page, int pageSize, String sort, boolean desc) {
        Sort sortData = desc ? Sort.by(Sort.Direction.DESC, sort) : Sort.by(Sort.Direction.ASC, sort);
        return storageDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<String> namePath = root.get("name");
            Path<String> keyPath = root.get("key");
            Path<String> deletePath = root.get("deleted");
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtil.empty(name)) {
                predicateList.add(criteriaBuilder.like(namePath, "%" + name + "%"));
            }
            if (!StringUtil.empty(key)) {
                predicateList.add(criteriaBuilder.equal(keyPath, "%" + key + "%"));
            }
            predicateList.add(criteriaBuilder.equal(deletePath, false));
            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        }, PageRequest.of(page, pageSize, sortData));
    }

    @Override
    public StorageData create(MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new BackendException(CodeEnum.FAIL_FILE_NAME_NULL);
        }
        String key = generateKey(file.getOriginalFilename());
        storage.store(file.getInputStream(), file.getSize(),
                file.getContentType(), key);
        String url = storage.generateUrl(key);
        StorageData storageData = new StorageData();
        storageData.setKey(key);
        storageData.setName(file.getOriginalFilename());
        storageData.setSize(file.getSize());
        storageData.setType(file.getContentType());
        storageData.setUrl(url);
        return storageDao.save(storageData);
    }

    @Override
    public ResponseEntity<Resource> loadAsResource(String keyName) {
        StorageData storageData = storageDao.findDistinctByKeyAndDeleted(keyName, false);
        if (storageData == null) {
            throw new BackendException(CodeEnum.KEY_NAME_NULL);
        }
        if (keyName.contains("../")) {
            throw new BackendException(CodeEnum.FAIL_PARAMS);
        }
        String type = storageData.getType();
        MediaType mediaType = MediaType.parseMediaType(type);
        Resource file = storage.loadAsResource(keyName);
        if (file == null) {
            throw new BackendException(CodeEnum.KEY_NAME_NULL);
        }
        return ResponseEntity.ok().contentType(mediaType).body(file);
    }

    @Override
    public StorageData read(long id) {
        return storageDao.findByIdAndDeleted(id, false);
    }

    @Override
    public boolean delete(long id) {
        try {
            StorageData storageData = storageDao.findByIdAndDeleted(id, false);
            if (storageData == null) {
                return true;
            }
            storage.delete(storageData.getKey());
            return true;
        } catch (Exception e) {
            log.error("delete", e);
            return false;
        }
    }

    @Override
    public ResponseEntity<Resource> download(String key) {
        StorageData storageData = storageDao.findDistinctByKeyAndDeleted(key, false);
        if (storageData == null) {
            throw new BackendException(CodeEnum.KEY_NAME_NULL);
        }
        if (key.contains("../")) {
            throw new BackendException(CodeEnum.FAIL_PARAMS);
        }
        String type = storageData.getType();
        MediaType mediaType = MediaType.parseMediaType(type);
        Resource file = storage.loadAsResource(key);
        if (file == null) {
            throw new BackendException(CodeEnum.KEY_NAME_NULL);
        }
        return ResponseEntity.ok().contentType(mediaType).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        return String.format("%s_%d", originalFilename.substring(index), System.currentTimeMillis());
    }
}

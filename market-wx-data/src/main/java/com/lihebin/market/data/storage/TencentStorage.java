package com.lihebin.market.data.storage;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @description: 腾讯对象存储
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/31 9:17 下午
 */
@Slf4j
@Service
public class TencentStorage implements Storage{

    @Value("${market.storage.tencent.secretId}")
    private String secretId;

    @Value("${market.storage.tencent.secretKey}")
    private String secretKey;

    @Value("${market.storage.tencent.region}")
    private String region;

    @Value("${market.storage.tencent.bucketName}")
    private String bucketName;

    private COSClient cosClient;

    private COSClient getCOSClient() {
        if (cosClient == null) {
            // 1 初始化用户身份信息(secretId, secretKey)
            COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
            // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
            ClientConfig clientConfig = new ClientConfig(new Region(region));
            cosClient = new COSClient(cred, clientConfig);
        }

        return cosClient;
    }

    @Override
    public void store(InputStream inputStream, long contentLength, String contentType, String keyName) {
        try {
            // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(contentLength);
            objectMetadata.setContentType(contentType);
            // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名 `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg`
            // 中，对象键为 doc1/pic1.jpg, 详情参考 [对象键](https://cloud.tencent.com/document/product/436/13324)
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, keyName, inputStream, objectMetadata);
            getCOSClient().putObject(putObjectRequest);
        } catch (Exception ex) {
            log.error("store", ex);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String keyName) {
        return null;
    }

    @Override
    public Resource loadAsResource(String keyName) {
        try {
            URL url = new URL(getBaseUrl() + keyName);
            Resource resource = new UrlResource(url);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (MalformedURLException e) {
            log.error("loadAsResource", e);
        }
        return null;
    }

    @Override
    public void delete(String keyName) {
        try {
            getCOSClient().deleteObject(bucketName, keyName);
        } catch (Exception e) {
            log.error("delete", e);
        }
    }

    @Override
    public String generateUrl(String keyName) {
        return getBaseUrl() + keyName;
    }

    private String getBaseUrl() {
        return "https://" + bucketName + ".cos." + region + ".myqcloud.com/";
    }

}

package com.lihebin.market.data.storage;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/30 10:12 下午
 */
@Service
@Slf4j
public class LocalStorage implements Storage{

    @Value("${market.storage.local.address}")
    private String address;

    @Value("${market.storage.local.storagePath}")
    private String storagePath;

    private Path rootLocation;

    @PostConstruct
    public void initRootLocation() {
        this.rootLocation = Paths.get(storagePath);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            log.error("initRootLocation", e);
        }
    }


    @Override
    public void store(InputStream inputStream, long contentLength, String contentType, String keyName) {
        try {
            Files.copy(inputStream, rootLocation.resolve(keyName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + keyName, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(path -> rootLocation.relativize(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String keyName) {
        return rootLocation.resolve(keyName);
    }

    @Override
    public Resource loadAsResource(String keyName) {
        try {
            Path file = load(keyName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            log.error("loadAsResource", e);
            return null;
        }
    }

    @Override
    public void delete(String keyName) {
        Path file = load(keyName);
        try {
            Files.delete(file);
        } catch (IOException e) {
            log.error("delete", e);
        }
    }

    @Override
    public String generateUrl(String keyName) {
        return String.format("%s%s", address, keyName);
    }
}

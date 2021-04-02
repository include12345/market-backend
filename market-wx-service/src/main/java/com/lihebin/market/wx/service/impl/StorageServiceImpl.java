package com.lihebin.market.wx.service.impl;

import com.lihebin.market.data.dao.StorageDao;
import com.lihebin.market.data.model.StorageData;
import com.lihebin.market.data.storage.Storage;
import com.lihebin.market.enums.CodeEnum;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.utils.StringUtil;
import com.lihebin.market.wx.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/4/1 10:18 下午
 */
@Service
public class StorageServiceImpl implements StorageService {


    @Value("${market.storage.active}")
    private String active;

    @Autowired
    private StorageDao storageDao;

    @Autowired
    private Map<String, Storage> storageMap;



    @Override
    public Page<StorageData> list(String key, String name, int page, int pageSize, String sort, boolean desc) {
        Sort sortData = desc ? Sort.by(Sort.Direction.DESC, sort) : Sort.by(Sort.Direction.ASC, sort);
        return storageDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<String> namePath = root.get("name");
            Path<String> keyPath = root.get("key");
            List<Predicate> predicateList = new ArrayList<>();
            if (!StringUtil.empty(name)) {
                predicateList.add(criteriaBuilder.like(namePath, "%" + name + "%"));
            }
            if (!StringUtil.empty(key)) {
                predicateList.add(criteriaBuilder.like(keyPath, "%" + key + "%"));
            }
            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        }, PageRequest.of(page, pageSize, sortData));
    }

    @Override
    public StorageData create(MultipartFile file) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new BackendException(CodeEnum.FAIL_FILE_NAME_NULL);
        }
        Storage storage = storageMap.get(active);
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


    private String generateKey(String originalFilename) {
        int index = originalFilename.lastIndexOf('.');
        return String.format("%s_%d", originalFilename.substring(index), System.currentTimeMillis());
    }
}

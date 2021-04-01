package com.lihebin.market.wx.service.impl;

import com.lihebin.market.data.dao.StorageDao;
import com.lihebin.market.data.model.StorageData;
import com.lihebin.market.utils.StringUtil;
import com.lihebin.market.wx.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/4/1 10:18 下午
 */
@Service
public class StorageServiceImpl implements StorageService {


    @Autowired
    private StorageDao storageDao;

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
}

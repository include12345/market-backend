package com.lihebin.market.wx.service.impl;

import com.lihebin.market.data.dao.AdDao;
import com.lihebin.market.data.model.AdData;
import com.lihebin.market.wx.domain.resp.AdResult;
import com.lihebin.market.wx.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lihebin on 2021/5/16.
 */
@Service
public class AdServiceImpl implements AdService{

    @Autowired
    private AdDao adDao;
    @Override
    public Page<AdResult> listAd(String name, String content, PageRequest pageRequest) {
        Page<AdData> adPage = adDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            Path<String> namePath = root.get("name");
            Path<String> contentPath = root.get("content");
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.like(namePath, "%" + name + "%"));
            predicateList.add(criteriaBuilder.like(contentPath, "%" + content + "%"));
            Predicate[] p = new Predicate[predicateList.size()];
            return criteriaBuilder.and(predicateList.toArray(p));
        }, pageRequest);
        return new PageImpl<>(
                adPage.getContent()
                        .stream()
                        .map(this::buildAdRes)
                        .collect(Collectors.toList()),
                adPage.previousPageable(), adPage.getTotalElements());
    }
}

package com.lihebin.market.wx.service;

import com.lihebin.market.data.model.AdData;
import com.lihebin.market.wx.domain.resp.AdResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest; /**
 * Created by lihebin on 2021/5/16.
 */
public interface AdService {


    /**
     * 查询推广列表
     *
     * @param name
     * @param content
     * @param pageRequest
     * @return
     */
    Page<AdResult> listAd(String name, String content, PageRequest pageRequest);



    /**
     * 参数转义
     * @param adData
     * @return
     */
    default AdResult buildAdRes(AdData adData) {
        AdResult adResult = new AdResult();
        adResult.setId(adData.getId());
        adResult.setName(adData.getName());
        adResult.setLink(adData.getLink());
        adResult.setContent(adData.getContent());
        adResult.setUrl(adData.getUrl());
        adResult.setPosition(adData.getPosition());
        adResult.setStartTime(adData.getStartTime());
        adResult.setEndTime(adData.getEndTime());
        adResult.setEnabled(adData.getEnabled());
        adResult.setCtime(adData.getCtime());
        adResult.setMtime(adData.getMtime());
        return adResult;
    }
}

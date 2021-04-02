package com.lihebin.market.wx.service;

import com.lihebin.market.data.model.StorageData;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @description: some desc
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/4/1 10:18 下午
 */
public interface StorageService {

    /**
     * 获取对象存储列表
     *
     * @param key
     * @param name
     * @param page
     * @param pageSize
     * @param sort
     * @param desc
     * @return
     */
    Page<StorageData> list(String key, String name, int page, int pageSize, String sort, boolean desc);

    /**
     * 存储一个文件对象
     *
     * @param file
     * @return
     */
    StorageData create(MultipartFile file) throws IOException;
}

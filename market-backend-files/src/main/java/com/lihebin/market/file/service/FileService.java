package com.lihebin.market.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lihebin on 2020/8/15.
 */
public interface FileService {

    /**
     * 上传图片至云存储，获取返回的url
     * @param file
     * @return
     */
    String uploadPicture(MultipartFile file);
}

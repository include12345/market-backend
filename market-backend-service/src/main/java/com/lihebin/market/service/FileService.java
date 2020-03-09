package com.lihebin.market.service;

import com.lihebin.market.params.FileRes;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lihebin on 2019/4/4.
 */
public interface FileService {
    /**
     * 上传图片至云存储，获取返回的url
     * @param file
     * @return
     */
    FileRes uploadPicture(MultipartFile file);

    /**
     * 获取上传文件所需的token
     * @param filename
     * @param size
     * @return
     */
    FileRes uploadPictureToken(String filename, String size);
}

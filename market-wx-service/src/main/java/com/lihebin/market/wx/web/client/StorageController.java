package com.lihebin.market.wx.web.client;

import com.lihebin.market.bean.Result;
import com.lihebin.market.data.model.StorageData;
import com.lihebin.market.utils.ResultUtil;
import com.lihebin.market.wx.annotation.LoginUser;
import com.lihebin.market.wx.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.IOException;

/**
 * @description: 对象存储服务接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/27 10:35 下午
 */
@RestController
@RequestMapping("/client/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;


    /**
     * 上传图片
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<StorageData> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return ResultUtil.ok(storageService.create(file));
    }


    /**
     * 访问存储对象
     *
     * @param key 存储对象key
     * @return
     */
    @GetMapping("/fetch/{key:.+}")
    public Result<ResponseEntity<Resource>> index(@PathVariable String key) {
        return ResultUtil.ok(storageService.loadAsResource(key));
    }

    /**
     * 访问存储对象
     *
     * @param key 存储对象key
     * @return
     */
    @GetMapping("/download/{key:.+}")
    public Result<ResponseEntity<Resource>> download(@PathVariable String key) {
        return ResultUtil.ok(storageService.download(key));
    }

}

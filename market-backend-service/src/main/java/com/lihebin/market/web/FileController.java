package com.lihebin.market.web;

import com.lihebin.market.bean.Result;
import com.lihebin.market.service.FileService;
import com.lihebin.market.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by lihebin on 2019/4/4.
 */
@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/file/uploadPicture", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadPicture(@RequestParam("file") MultipartFile file) {
        return ResultUtil.success(fileService.uploadPicture(file));
    }

    @RequestMapping(value = "/file/uploadPictureToken",  method = RequestMethod.GET)
    @ResponseBody
    public Result uploadPictureToken(@RequestParam(value = "filename", required = true) String filename, @RequestParam(value = "size", required = true) String size) {
        return ResultUtil.success(fileService.uploadPictureToken(filename, size));
    }
}

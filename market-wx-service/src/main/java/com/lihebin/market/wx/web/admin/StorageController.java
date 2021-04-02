package com.lihebin.market.wx.web.admin;

import com.lihebin.market.bean.Result;
import com.lihebin.market.data.model.StorageData;
import com.lihebin.market.utils.ResultUtil;
import com.lihebin.market.wx.annotation.RequiresPermissionsDesc;
import com.lihebin.market.wx.domain.StorageReq;
import com.lihebin.market.wx.service.StorageService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * 对象存储模块
 */
@RestController
@RequestMapping("/admin/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequiresPermissions("admin:storage:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "查询")
    @GetMapping("/list")
    public Result<Page<StorageData>> list(String key, String name,
                                          @RequestParam(defaultValue = "1") Integer page,
                                          @RequestParam(defaultValue = "10") Integer pageSize,
                                          @RequestParam(defaultValue = "addTime") String sort,
                                          @RequestParam(defaultValue = "true") Boolean desc) {
        return ResultUtil.ok(storageService.list(key, name, page, pageSize, sort, desc));
    }

    @RequiresPermissions("admin:storage:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "上传")
    @PostMapping("/create")
    public Result<StorageData> create(@RequestParam("file") MultipartFile file) throws IOException {
            return ResultUtil.ok(storageService.create(file));
    }

    @RequiresPermissions("admin:storage:read")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "详情")
    @GetMapping("/read")
    public Result<StorageData> read(@NotNull Long id) {
        return ResultUtil.ok(storageService.read(id));
    }

    @RequiresPermissions("admin:storage:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "编辑")
    @PostMapping("/update")
    public Result update(@RequestBody StorageReq storageReq) {
        //todo
        return null;
    }

    @RequiresPermissions("admin:storage:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "对象存储"}, button = "删除")
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody StorageReq storageReq) {
        return ResultUtil.ok(storageService.delete(storageReq.getId()));
    }

}



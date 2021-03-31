package com.lihebin.market.data.storage;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @description: 对象存储接口
 * @author: lihebin
 * @email: include_lihebin@163.com
 * @date: 2021/3/30 10:09 下午
 */
public interface Storage {

    /**
     * 存储一个文件对象
     *
     * @param inputStream   文件输入流
     * @param contentLength 文件长度
     * @param contentType   文件类型
     * @param keyName       文件名
     */
    void store(InputStream inputStream, long contentLength, String contentType, String keyName);

    /**
     * 读取所有
     *
     * @return
     */
    Stream<Path> loadAll();

    /**
     * 根据name读取文件路径
     *
     * @param keyName
     * @return
     */
    Path load(String keyName);

    /**
     * 根据name读取文件流
     *
     * @param keyName
     * @return
     */
    Resource loadAsResource(String keyName);

    /**
     * 根据name删除文件
     *
     * @param keyName
     */
    void delete(String keyName);

    /**
     * 根据keyName获取url
     *
     * @param keyName
     * @return
     */
    String generateUrl(String keyName);
}

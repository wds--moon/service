package com.shandong.culture.search.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @Description: 上传接口
 * @Author: moon
 * @CreateDate: 2019/5/9 0009 11:24
 * @UpdateUser: moon
 * @UpdateDate: 2019/5/9 0009 11:24
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public interface StorageService {

    /**
     * 上传图片(图片需要压缩)
     *  返回存储路径
     * @param file
     */
    Map<String,String> storeImg(MultipartFile file);

    /**
     * 上传压缩文件
     * 返回存储路径
     * @param file
     */
    String storeZip(MultipartFile file);


}

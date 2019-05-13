package com.shandong.culture.search.web;

import com.shandong.culture.search.model.ResponseVO;
import com.shandong.culture.search.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

/**
 * @author moon
 * @create 2019-05-09 11:18
 **/
@Api("文件上传处理类")
@RestController
@RequestMapping("/uploads")
public class FileUploadController {


    @Autowired
    StorageService storageService;

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @PostMapping(params = {"img"})
    public Map<String, String> handelImgUpload(@RequestParam("img") MultipartFile file, RedirectAttributes redirectAttributes) {
        Map<String, String> map = storageService.storeImg(file);
        return map;
    }

    @ApiOperation(value = "上传附件", notes = "上传附件")
    @PostMapping(params = {"zip"})
    public String handelZipUpload(@RequestParam("zip") MultipartFile file) {
        String s = storageService.storeZip(file);
        return s;
    }
}

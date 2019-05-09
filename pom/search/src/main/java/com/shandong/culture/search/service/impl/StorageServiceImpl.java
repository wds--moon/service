package com.shandong.culture.search.service.impl;

import com.shandong.culture.search.service.StorageService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author moon
 * @create 2019-05-09 11:24
 **/
@Service
public class StorageServiceImpl implements StorageService {


    @Value("${uplaod_file_path}")
    private String uplaodImgPath;

    @Value("${downlaod_file_root_url}")
    private String downlaodFileRootUrl;


    @Override
    public Map<String, String> storeImg(MultipartFile file) {
        try {

            if (file.isEmpty()) {
                throw new IllegalArgumentException("Failed to store empty file " + file.getOriginalFilename());
            }
            /**
             * 存储目录
             */
            String createDir = uplaodImgPath + File.separator + "img" + File.separator + LocalDate.now() + File.separator;
            /**
             * http访问路径
             */
            String httpUrl = downlaodFileRootUrl + "img/" + LocalDate.now() + "/";
            Path path = Paths.get(createDir);
            if (!Files.exists(path)) {
                path = Files.createDirectories(path);
            }
            String originalFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String rep = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
            String imgdir = createDir + File.separator + uuid + rep;
            String httpSmallUrl = httpUrl + "small_" + uuid + rep;
            httpUrl = httpUrl + uuid + rep;
            Files.copy(file.getInputStream(), path.resolve(imgdir));
            /**
             * 压缩存储的路径,但是png的图片需要转换为jpg的进行压缩
             */
            return thumbnailImg(createDir, httpUrl, rep, imgdir, httpSmallUrl,uuid);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    private Map<String, String> thumbnailImg(String createDir, String httpUrl, String rep, String imgdir, String httpSmallUrl,String uuid) throws IOException {
        Map<String, String> imgs = new HashMap<>();
        String thumbnaildir = createDir + "small_" + uuid + rep;
        if (thumbnaildir.contains(".png")) {
            thumbnaildir = thumbnaildir.replace(".png", ".jpg");
            httpSmallUrl = httpSmallUrl.replace(".png", ".jpg");
        }
        Thumbnails.of(imgdir)
                .scale(1f)
                .outputQuality(0.5f)
                .toFile(thumbnaildir);
        imgs.put("httpUrl", httpUrl);
        imgs.put("httpSmallUrl", httpSmallUrl);
        return imgs;
    }

    @Override
    public String storeZip(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("Failed to store empty file " + file.getOriginalFilename());
            }
            String createDir = uplaodImgPath + File.separator + "zip" + File.separator + LocalDate.now() + File.separator;
            String url = downlaodFileRootUrl + "zip/" + LocalDate.now() + "/";
            Path path = Paths.get(createDir);
            if (!Files.exists(path)) {
                path = Files.createDirectories(path);
            }
            String originalFilename = file.getOriginalFilename();
            String rep = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
            String uuid = UUID.randomUUID().toString();
            String urlzip = createDir + File.separator + uuid + rep;
            url = url + uuid + rep;
            Files.copy(file.getInputStream(), path.resolve(urlzip));
            return url;
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

}

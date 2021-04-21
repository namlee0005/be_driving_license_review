package com.nam_dev.driving_license_review.service;


import com.nam_dev.driving_license_review.control.base.ApiErrorCode;
import com.nam_dev.driving_license_review.control.base.ApiException;
import com.nam_dev.driving_license_review.model.base.BaseResponse;
import com.nam_dev.driving_license_review.model.base.ResponseImpl;
import com.nam_dev.driving_license_review.model.mongo.ImageMongo;
import com.nam_dev.driving_license_review.mongo.ImageRepository;
import com.nam_dev.driving_license_review.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Service
@Slf4j
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    private final TaskExecutor taskExecutor;

    private final Map<String, Boolean> mapExtFile = new HashMap<>();
    private final Map<String, Boolean> videoExt = new HashMap<>();

    public ImageService(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
        mapExtFile.put(".jpg", true);
        mapExtFile.put(".jpeg", true);
        mapExtFile.put(".png", true);
        mapExtFile.put(".mpa", true);
        mapExtFile.put(".aif", true);
        mapExtFile.put(".mid", true);
        mapExtFile.put(".wma", true);
        mapExtFile.put(".csv", true);
        mapExtFile.put(".gif", true);
        mapExtFile.put(".ico", true);
        mapExtFile.put(".psd", true);
        mapExtFile.put(".tif", true);
        mapExtFile.put(".heif", true);
        mapExtFile.put(".heic", true);
        // video
        videoExt.put(".avi", true);
        videoExt.put(".mov", true);
        videoExt.put(".mp3", true);
        videoExt.put(".mp4", true);
    }

    public BaseResponse<List<String>> uploadImage(String token, MultipartFile[] files, String type) {
        if (files == null || files.length == 0) return ResponseImpl.ok().with(-1, "File not found!").build();
        List<String> exts = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            int index = files[i].getOriginalFilename().lastIndexOf(".");
            if (index == -1) return ResponseImpl.ok().with(-1, "File not valid!").build();
            exts.add(files[i].getOriginalFilename().substring(index).toLowerCase());
            Map<String, Boolean> validType = type.equals("video") ? videoExt : mapExtFile;
            if (!validType.containsKey(exts.get(i))) return ResponseImpl.ok().with(-1, "File not valid!").build();
        }
        String folder = "";
        switch (token) {
            case "SYSTEM_IMAGE_DLR":
                folder = "dlr";
                break;
            case "SYSTEM_IMAGE_OTHER":
                folder = "other";
                break;
            default:
                return ResponseImpl.ok().with(0, "Token not valid!").build();
        }
        File file = new File(System.getProperty("user.dir") + "/" + folder);
        log.info("*************** path: {} *****************", file);
        if (!file.exists()) file.mkdir();
        String pathUrl = "";
        if (type.equals("video")) folder += "/video";
        else folder +=
                "/"
                        + TimeUtils.convertTimeStampToString(
                        System.currentTimeMillis(), TimeUtils.DATE_FORMAT_20);
        pathUrl = folder;
        file = new File(System.getProperty("user.dir") + "/" + folder);
        if (!file.exists()) file.mkdir();
        List<String> returnUrl = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            pathUrl = folder + "/" + UUID.randomUUID().toString() + exts.get(i);
            file = new File(System.getProperty("user.dir") + "/" + pathUrl);
            try {
                if (!file.exists() && !file.createNewFile()) {
                    return ResponseImpl.ok().with(-1, "Cannot write file!").build();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (InputStream inputStream = files[i].getInputStream(); OutputStream outStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            returnUrl.add(pathUrl);
            //            if (".jpg".equals(exts.get(i)) || ".jpeg".equals(exts.get(i)) ||
            // ".png".equals(exts.get(i))) {
            //                downSizeImge(pathUrl, exts.get(i));
            //            }
        }
        saveToMongo(returnUrl);
        taskExecutor.execute(() -> createThumbnails(returnUrl));
        return ResponseImpl.ok().with(returnUrl).build();
    }

    public void createThumbnails(List<String> pathUrl) {
        for (String path: pathUrl) {
            File fileData = new File(System.getProperty("user.dir") + "/" + path);
            String[] s = path.split("/");
            File outputDirectory = new File(System.getProperty("user.dir") + "/" + s[0] + "/" + s[1]);
            if (!fileData.exists()) continue;
            try {
                BufferedImage bufferedImage = ImageIO.read(fileData);
                Thumbnails.of(fileData)
                        .size(bufferedImage.getWidth(), bufferedImage.getHeight())
                        .toFiles(outputDirectory, Rename.SUFFIX_HYPHEN_THUMBNAIL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToMongo(List<String> pathUrl) {
        List<ImageMongo> images = new ArrayList<>();
        pathUrl.forEach(
                url -> {
                    ImageMongo imageMongo = new ImageMongo();
                    imageMongo.setId(url);
                    imageMongo.setTime(System.currentTimeMillis());
                    imageMongo.setStatus(false);
                    imageMongo.setPath(url);
                    images.add(imageMongo);
                });
        imageRepository.saveAll(images);
    }
}

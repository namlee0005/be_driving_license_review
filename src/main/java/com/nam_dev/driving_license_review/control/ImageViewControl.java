package com.nam_dev.driving_license_review.control;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


@RestController
@RequestMapping("")
@CrossOrigin
public class ImageViewControl {

    @GetMapping(
            value = "image/{service}/{day}/{file}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] toImage(@PathVariable("service") String service,
                   @PathVariable("day") String day, @PathVariable("file") String file, @RequestParam(defaultValue = "false") boolean thumbnail) {
        File fileData = new File(System.getProperty("user.dir") + "/" + service + "/" + day + "/" + file);
        if (!fileData.exists()) return new byte[0];
        InputStream in;
        String[] ob = file.split("\\.");
        if (ob.length != 2) return new byte[0];
        String path = System.getProperty("user.dir") + "/" + service + "/" + day + "/" + ob[0] + "-thumbnail." + ob[1];
        try {
            File newFile = new File(path);
            if (!newFile.exists()) {
                File outputDirectory = new File(System.getProperty("user.dir") + "/" + service + "/" + day);
                BufferedImage bufferedImage = ImageIO.read(fileData);
                Thumbnails.of(fileData)
                        .size(bufferedImage.getWidth(), bufferedImage.getHeight())
                        .toFiles(outputDirectory, Rename.SUFFIX_HYPHEN_THUMBNAIL);
            }

            if (!thumbnail) {
                in = new FileInputStream(fileData);
            } else {
                in = new FileInputStream(newFile);
            }
            return IOUtils.toByteArray(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @GetMapping(
            value = "file/{service}/{day}/{file}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody
    byte[] toFile(@PathVariable("service") String service,
                  @PathVariable("day") String day, @PathVariable("file") String file) {
        File fileData = new File(System.getProperty("user.dir") + "/" + service + "/" + day + "/" + file);
        InputStream in = null;
        try {
            in = new FileInputStream(fileData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return IOUtils.toByteArray(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}

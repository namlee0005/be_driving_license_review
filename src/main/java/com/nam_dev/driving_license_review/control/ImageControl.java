package com.nam_dev.driving_license_review.control;


import com.nam_dev.driving_license_review.model.base.BaseResponse;
import com.nam_dev.driving_license_review.service.ImageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1.0/image")
public class ImageControl {
    @Autowired
    private ImageService imageService ;

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ApiOperation(" du lieu tra ve la array path url anh\n" +
            " nguoi dung chi can truyen url do vao api get anh\n" +
            " vi du: url+ image/dlr/11042020/d59e1f29-51af-4143-8d0e-38b8280584c5.jpg\n" +
            " neu la file: url+ file/dlr/11042020/d59e1f29-51af-4143-8d0e-38b8280584c5.mp4")
    public ResponseEntity<BaseResponse<List<String>>> uploadImage(
            @ApiParam("Token dich nao dung thi dang ki folder de gen token dich vu do. " +
                    "hien tai co 2 token: SYSTEM_IMAGE_DLR va SYSTEM_IMAGE_OTHER")
            @RequestHeader("Authorization") String token, @RequestParam("files") MultipartFile[] files) {
        return ResponseEntity.ok().body(imageService.uploadImage(token, files, "image"));
    }

}

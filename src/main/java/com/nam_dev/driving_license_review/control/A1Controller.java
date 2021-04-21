package com.nam_dev.driving_license_review.control;

import com.nam_dev.driving_license_review.model.base.BaseResponse;
import com.nam_dev.driving_license_review.model.mongo.A1Mongo;
import com.nam_dev.driving_license_review.service.A1Service;
import com.nam_dev.driving_license_review.service.ImageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author namkx
 * Create at 4/20/21
 */
@RestController
@CrossOrigin
@RequestMapping("api/v1.0/a1")
public class A1Controller {
    @Autowired
    private A1Service a1Service;

    @GetMapping
    @ApiOperation(" Lay tat ca cac cau hoi cua bo de a1")
    public ResponseEntity<BaseResponse<List<A1Mongo>>> uploadImage(
            @ApiParam("TOKEN = TOKEN_DLR")
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(a1Service.getAll(token));
    }
}

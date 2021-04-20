package com.nam_dev.driving_license_review.control;


import com.nam_dev.driving_license_review.model.base.BaseResponse;
import com.nam_dev.driving_license_review.service.ImageService;
import com.nam_dev.driving_license_review.service.VideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("api/v1.0/")
@CrossOrigin
public class VideoController {
  @Autowired
  ImageService imageService;

  @Autowired
  VideoService videoService;

  @RequestMapping(value = "video/upload", method = RequestMethod.POST)
  @ApiOperation("API Update video")
  public ResponseEntity<BaseResponse<List<String>>> uploadImage(
      @RequestHeader("Authorization") String token, @RequestParam("files") MultipartFile[] files) {
    return ResponseEntity.ok().body(imageService.uploadImage(token, files, "video"));
  }

  @GetMapping("ibg/video/{path}")
  @ApiOperation("API Get video (stream)")
  public Mono<ResponseEntity<byte[]>> streamVideo(
      @RequestHeader(value = "Range", required = false) String httpRangeList,
      @PathVariable("path") String path) {
    return Mono.just(videoService.prepareContent(path, httpRangeList));
  }
}

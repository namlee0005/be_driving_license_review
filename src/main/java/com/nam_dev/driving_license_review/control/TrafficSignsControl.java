package com.nam_dev.driving_license_review.control;

import com.nam_dev.driving_license_review.model.base.BaseResponse;
import com.nam_dev.driving_license_review.model.base.PageResponse;
import com.nam_dev.driving_license_review.model.base.ResponseImpl;
import com.nam_dev.driving_license_review.model.mongo.A1Mongo;
import com.nam_dev.driving_license_review.model.mongo.TrafficSignsMongo;
import com.nam_dev.driving_license_review.service.A1Service;
import com.nam_dev.driving_license_review.service.TrafficSignsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1.0/traffic-signs")
public class TrafficSignsControl {
    @Autowired
    private TrafficSignsService trafficSignsService;

    @GetMapping
    @ApiOperation(" Lay image cua bien bao")
    public ResponseEntity<BaseResponse<PageResponse<List<TrafficSignsMongo>>>> findByType(
            @ApiParam("TOKEN = TOKEN_DLR")
            @RequestHeader("Authorization") String token,
            @RequestParam Integer page,
            @RequestParam Integer perPage,
            @RequestParam Integer type
            ) {
        return ResponseEntity.ok().body(ResponseImpl.ok().with(trafficSignsService.findByType(token, type, page, perPage)).build());
    }
}

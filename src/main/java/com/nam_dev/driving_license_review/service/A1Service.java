package com.nam_dev.driving_license_review.service;

import com.nam_dev.driving_license_review.control.base.ApiErrorCode;
import com.nam_dev.driving_license_review.control.base.ApiException;
import com.nam_dev.driving_license_review.model.base.BaseResponse;
import com.nam_dev.driving_license_review.model.base.ResponseImpl;
import com.nam_dev.driving_license_review.model.mongo.A1Mongo;
import com.nam_dev.driving_license_review.mongo.A1MongoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author namkx
 * Create at 4/20/21
 */
@Service
@AllArgsConstructor
public class A1Service {

    private final A1MongoRepository a1MongoRepository;

    public BaseResponse<List<A1Mongo>> getAll(String token) {
        if (!token.equals("TOKEN_DLR")) throw new ApiException(ApiErrorCode.UNAUTHORIZED);
        return ResponseImpl.ok().with(a1MongoRepository.findAll()).build();
    }

}

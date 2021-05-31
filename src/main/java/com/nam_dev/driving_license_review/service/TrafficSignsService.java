package com.nam_dev.driving_license_review.service;

import com.nam_dev.driving_license_review.control.base.ApiErrorCode;
import com.nam_dev.driving_license_review.control.base.ApiException;
import com.nam_dev.driving_license_review.model.base.BaseResponse;
import com.nam_dev.driving_license_review.model.base.PageResponse;
import com.nam_dev.driving_license_review.model.base.ResponseImpl;
import com.nam_dev.driving_license_review.model.mongo.TrafficSignsMongo;
import com.nam_dev.driving_license_review.mongo.TrafficSignsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrafficSignsService {

    private final TrafficSignsRepository trafficSignsRepository;

    public TrafficSignsService(TrafficSignsRepository trafficSignsRepository) {
        this.trafficSignsRepository = trafficSignsRepository;
    }

    public PageResponse<List<TrafficSignsMongo>> findByType(String token, Integer type, Integer page, Integer perPage) {
        if (!token.equals("TOKEN_DLR")) throw new ApiException(ApiErrorCode.UNAUTHORIZED);
        Pageable pageable = PageRequest.of(page, perPage);

        Page<TrafficSignsMongo> trafficSignsMongoPage = trafficSignsRepository.findByType(type, pageable);

        PageResponse<List<TrafficSignsMongo>> pageResponse = new PageResponse<>();
        pageResponse.setLength(perPage);
        pageResponse.setPageIndex(page);
        pageResponse.setTotalPage(trafficSignsMongoPage.getTotalPages());
        pageResponse.setData(trafficSignsMongoPage.getContent());

        return pageResponse;
    }
}

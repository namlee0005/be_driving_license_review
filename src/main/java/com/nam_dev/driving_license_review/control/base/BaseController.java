package com.nam_dev.driving_license_review.control.base;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    private ObjectMapper mapper;

    @Autowired
    public BaseController() {
        mapper = new ObjectMapper();
    }


}

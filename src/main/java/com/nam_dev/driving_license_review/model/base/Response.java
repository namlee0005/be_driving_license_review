package com.nam_dev.driving_license_review.model.base;

public interface Response {
    Response with(int status, String message);

    Response with(Object data);

    <T> BaseResponse<T> build();
}

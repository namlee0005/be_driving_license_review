package com.nam_dev.driving_license_review.control.base;


import com.nam_dev.driving_license_review.model.base.BaseResponse;
import com.nam_dev.driving_license_review.model.base.ResponseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<BaseResponse<Void>> handlerApiException(ApiException ex) {
        if (ex == null) {
            return build(0, "Loi khong xac dinh");
        }
        LOGGER.error(" detail : {}", ex.getObjectValue());

        if (null != ex.getObjectName()) {
            String message = "";
            if (ex.getIsPrevious() != null && ex.getIsPrevious() == 0) {
                message = ex.getApiErrorCode().getMessage() + " " + ex.getObjectName();
            } else {
                message = ex.getObjectName() + " " + ex.getApiErrorCode().getMessage();
            }

            return build(ex.getApiErrorCode().getErrorCode(), message);
        }

        if (null != ex.getObjectValue()) {
            String message = "";
            if (ex.getIsPrevious() != null && ex.getIsPrevious() == 0) {
                message = ex.getApiErrorCode().getMessage() + " " + ex.getObjectValue();
            } else {
                message = ex.getObjectValue() + " " + ex.getApiErrorCode().getMessage();
            }
            return build(ex.getApiErrorCode().getErrorCode(), message);
        }


        return build(ex.getApiErrorCode().getErrorCode(), ex.getApiErrorCode().getMessage());

    }

    public ResponseEntity<BaseResponse<Void>> build(Integer errorCode, String message) {
        return ResponseEntity.ok().body(
                ResponseImpl.ok().with(errorCode, message).build());
    }
}

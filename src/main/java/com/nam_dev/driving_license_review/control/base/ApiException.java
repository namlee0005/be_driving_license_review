package com.nam_dev.driving_license_review.control.base;


/**
 * @author namkx
 * Create at 3/28/20
 */

public class ApiException extends RuntimeException {
    private String objectName;
    private Object objectValue;
    private ApiErrorCode apiErrorCode;
    private Integer previous;


    public ApiException(String objectName, Object objectValue, ApiErrorCode apiErrorCode) {
        this.objectName = objectName;
        this.objectValue = objectValue;
        this.apiErrorCode = apiErrorCode;
    }

    public ApiException(Object objectValue, ApiErrorCode apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
        this.objectValue = objectValue;
    }

    public ApiException(String objectName, ApiErrorCode apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
        this.objectName = objectName;
    }

    public ApiException(ApiErrorCode apiErrorCode) {
        super();
        this.apiErrorCode = apiErrorCode;
    }

    public ApiException(ApiErrorCode apiErrorCode, Object objectValue) {
        this.apiErrorCode = apiErrorCode;
        this.objectValue = objectValue;
        this.previous = 0;
    }

    public ApiErrorCode getApiErrorCode() {
        return apiErrorCode;
    }

    public void setApiErrorCode(ApiErrorCode apiErrorCode) {
        this.apiErrorCode = apiErrorCode;
    }

    public String getObjectName() {
        return objectName;
    }

    public Object getObjectValue() {
        return objectValue;
    }

    public Integer getIsPrevious() {
        return previous;
    }
}

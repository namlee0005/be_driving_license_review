package com.nam_dev.driving_license_review.model.base;


public class ResponseImpl implements Response {
    private int errorCode;
    private String message;
    private Object data;

    public ResponseImpl() {
        errorCode = 1;
        message = "Success";
    }

    public static Response ok() {
        return new ResponseImpl();
    }

    @Override
    public Response with(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        return this;
    }

    @Override
    public Response with(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public <T> BaseResponse<T> build() {
        return new BaseResponse<T>(errorCode, message, (T) data);
    }
}

package com.nam_dev.driving_license_review.control.base;

/**
 * @author namkx Create at 3/28/20
 */
public enum ApiErrorCode {
    UNAUTHORIZED(-1, "Unauthorized"),
    STRING_EMPTY(-2, "Dữ liệu không được bỏ trống"),
    INCORRECT_HEADER_REQUEST(-3, "Incorrect header request"),
    PHONE_INCORRECT(-4, "Số điện thoại không hợp lệ, xin vui lòng nhập lại"),
    EMAIL_INCORRECT(-5, "Địa chỉ email không hợp lệ, xin vui lòng nhập lại"),
    OBJECT_NOT_FOUND(-6, "không tồn tại"),
    OBJECT_EXIST(-7, "đã tồn tại"),
    TOKEN_EXPIRED(-8, "Phiên làm việc của bạn đã hết hạn"),
    ID_NOT_FOUND(-11, "not exist"),
    USERNAME_INCORRECT(-20, "Tên đăng nhập phải là chuỗi không dấu 6-16 ký tự, chỉ gồm chữ cái, số và dấu _"),
    PASSWORD_INCORRECT(-21, "Mật khẩu phải là chuỗi không dấu 8-16 ký tự, chỉ gồm chữ cái, số"),
    IMAGE_IS_NULL_OR_IS_IMAGE_LINK(-84, "Image để trống hoặc nếu điền thì phải là link hình ảnh bắt đầu bằng http"),
    USER_BLOCKED(-83, "Tài khoản của bạn đã bị khoá, vui lòng liên hệ CSHK để được hỗ trợ"),
    ERROR_SEND_OTP(-88, "Có lỗi trong quá trình gửi OTP, vui lòng thử lại"),
    WAIT_TIME_TO_RESEND_SMS(-89, "Bạn vừa gửi sms, hãy kiểm tra sms nhận được"),
    TYPE_NOT_FOUND(-90, "Type không hợp lệ"),
    OBJECT_NOT_VALID(-9, "Object yêu cầu không hợp lệ"),
    ;

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    private Integer errorCode;
    private String message;

    ApiErrorCode(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}

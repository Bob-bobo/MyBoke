package com.dzb.myboke.Constant;

/**
 * @author zhengbo
 * @version 1.0
 * @date 2023/4/1 0:28
 */
public enum CodeType {
    /**
     * 返回状态
     */
    SUCCESS_STATUS(0, "访问成功"),

    USER_NOT_LOGIN(403, "用户未认证"),

    PERMISSION_VERIFY_FAIL(403, "用户验证失败"),

    USERNAME_EXIST(406, "用户名已经存在"),

    USERNAME_FROMAT_ERROR(407, "用户名格式错误"),

    PHONE_IS_REGISTERED(408, "手机号已被注册"),

    SERVICE_FAIL_STATUS(500, "服务报错");

    private int code;

    private String message;

    CodeType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

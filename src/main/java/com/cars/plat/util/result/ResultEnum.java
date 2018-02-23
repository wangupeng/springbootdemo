package com.cars.plat.util.result;

/**
 * Created by wangyupeng on 2017/12/19.
 */
public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    PRIMARY_SCHOOL(100, "我猜你可能还在上小学"),
    MIDDLE_SCHOOL(101, "你可能在上初中"),

    ADD_SUCCESS(1001,"新增成功！"),
    ADD_FAILED(1002,"新增失败！"),
    UPDATE_SUCCESS(1001,"修改成功！"),
    UPDATE_FAILED(1002,"修改失败！"),
    DEL_SUCCESS(1001,"删除成功！"),
    DEL_FAILED(1002,"删除失败！"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

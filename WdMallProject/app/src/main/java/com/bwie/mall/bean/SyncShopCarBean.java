package com.bwie.mall.bean;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/21 11:28:23
 * @Description:
 */
public class SyncShopCarBean {
    String status;
    String message;

    public SyncShopCarBean(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SyncShopCarBean{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

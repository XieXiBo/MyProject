package com.bwie.mall.bean;

import java.util.List;

public class FirstCategory {

    /**
     * result : [{"name":"女装","id":"1001002"},{"name":"男鞋","id":"1001003"},{"name":"女鞋","id":"1001004"},{"name":"美妆护肤","id":"1001007"},{"name":"手机数码","id":"1001008"},{"name":"箱包手袋","id":"1001005"}]
     * message : 查询成功
     * status : 0000
     */
    public List<ResultEntity> result;
    public String message;
    public String status;

    @Override
    public String toString() {
        return "FirstCategory{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class ResultEntity {
        /**
         * name : 女装
         * id : 1001002
         */
        public String name;
        public String id;

        @Override
        public String toString() {
            return "ResultEntity{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}

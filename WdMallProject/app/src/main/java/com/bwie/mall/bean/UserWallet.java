package com.bwie.mall.bean;

import java.util.List;

/**
 * @author xuhongtao    二级商品类目
 * @fileName UserWallet
 * @package com.bw.xuhongtao.bean.FirstCategory
 * @date 2019/3/22/022 19:18
 */
public class UserWallet {

    /**
     * result : [{"name":"外套","id":"1001002001"},{"name":"裙装","id":"1001002002"},{"name":"打底毛衣","id":"1001002003"},{"name":"卫衣","id":"1001002004"},{"name":"裤装","id":"1001002005"}]
     * message : 查询成功
     * status : 0000
     */
    public List<ResultEntity> result;
    public String message;
    public String status;

    @Override
    public String toString() {
        return "UserWallet{" +
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
         * name : 外套
         * id : 1001002001
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

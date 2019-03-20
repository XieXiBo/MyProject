package com.bwie.mall.bean;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/19 19:16:23
 * @Description:
 */
public class DetailsBean {

    /**
     * result : {"categoryId":"1001004004","categoryName":"板鞋","commentNum":0,"commodityId":22,"commodityName":"冬季保暖加绒女士棉鞋 韩版拼色低帮板鞋学生休闲鞋","describe":"米黑,36码","details":"<div class=\"dc-img\">\r\n    <div class=\"dc-img-detail\">\r\n                        <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/56/edb51370-f08c-4824-a993-3b65d59bdea5.jpg\" class=\"J-mer-bigImg\" data-original=\"http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/56/edb51370-f08c-4824-a993-3b65d59bdea5.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/93/a28dccfe-94b4-4ec6-b166-90bea38b71cd.jpg\" class=\"J-mer-bigImg\" data-original=\"http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/93/a28dccfe-94b4-4ec6-b166-90bea38b71cd.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/134/f3b5efc8-eb82-4fd2-b96d-8a9c9e6b5764.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/178/c7cd67ab-dc50-47ff-9429-0cd8304e00b4.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/44/da28e7b3-1f73-4ad7-a2cd-036157835972.jpg\">\r\n        <\/div>\r\n                <div class=\"img-6xx-bg\">\r\n            <img src=\"http://s2.vipstatic.com/img/share/blank.png\" class=\"lazy J-mer-bigImg\" data-original=\"http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/136/592a43c9-6619-42d1-9dfa-ab855a0f4902.jpg\">\r\n        <\/div>\r\n            <\/div>\r\n    <div class=\"dc-img-con\">\r\n            <\/div>\r\n    <div class=\"dc-txt-con\">\r\n            <\/div>\r\n<\/div>","picture":"http://172.17.8.100/images/small/commodity/nx/bx/5/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/5/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/5/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/5/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/5/5.jpg","price":119,"saleNum":0,"stock":9999,"weight":1}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
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

    public static class ResultBean {
        /**
         * categoryId : 1001004004
         * categoryName : 板鞋
         * commentNum : 0
         * commodityId : 22
         * commodityName : 冬季保暖加绒女士棉鞋 韩版拼色低帮板鞋学生休闲鞋
         * describe : 米黑,36码
         * details :
         * <div class="dc-img">
         <div class="dc-img-detail">
         <div class="img-6xx-bg">
         <img src="http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/56/edb51370-f08c-4824-a993-3b65d59bdea5.jpg" class="J-mer-bigImg" data-original="http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/56/edb51370-f08c-4824-a993-3b65d59bdea5.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/93/a28dccfe-94b4-4ec6-b166-90bea38b71cd.jpg" class="J-mer-bigImg" data-original="http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/93/a28dccfe-94b4-4ec6-b166-90bea38b71cd.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/134/f3b5efc8-eb82-4fd2-b96d-8a9c9e6b5764.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/178/c7cd67ab-dc50-47ff-9429-0cd8304e00b4.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/44/da28e7b3-1f73-4ad7-a2cd-036157835972.jpg">
         </div>
         <div class="img-6xx-bg">
         <img src="http://s2.vipstatic.com/img/share/blank.png" class="lazy J-mer-bigImg" data-original="http:http://a.vpimg4.com/upload/merchandise/pdcvis/112483/2017/1103/136/592a43c9-6619-42d1-9dfa-ab855a0f4902.jpg">
         </div>
         </div>
         <div class="dc-img-con">
         </div>
         <div class="dc-txt-con">
         </div>
         </div>
         * picture : http://172.17.8.100/images/small/commodity/nx/bx/5/1.jpg,http://172.17.8.100/images/small/commodity/nx/bx/5/2.jpg,http://172.17.8.100/images/small/commodity/nx/bx/5/3.jpg,http://172.17.8.100/images/small/commodity/nx/bx/5/4.jpg,http://172.17.8.100/images/small/commodity/nx/bx/5/5.jpg
         * price : 119
         * saleNum : 0
         * stock : 9999
         * weight : 1
         */

        private String categoryId;
        private String categoryName;
        private int commentNum;
        private int commodityId;
        private String commodityName;
        private String describe;
        private String details;
        private String picture;
        private int price;
        private int saleNum;
        private int stock;
        private int weight;

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "categoryId='" + categoryId + '\'' +
                    ", categoryName='" + categoryName + '\'' +
                    ", commentNum=" + commentNum +
                    ", commodityId=" + commodityId +
                    ", commodityName='" + commodityName + '\'' +
                    ", describe='" + describe + '\'' +
                    ", details='" + details + '\'' +
                    ", picture='" + picture + '\'' +
                    ", price=" + price +
                    ", saleNum=" + saleNum +
                    ", stock=" + stock +
                    ", weight=" + weight +
                    '}';
        }
    }
}

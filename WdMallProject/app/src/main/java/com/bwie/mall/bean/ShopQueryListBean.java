package com.bwie.mall.bean;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/21 18:54:28
 * @Description:
 */
public class ShopQueryListBean {
    @Override
    public String toString() {
        return "ShopQueryListBean{" +
                "commodityId=" + commodityId +
                ", count=" + count +
                '}';
    }

    private int commodityId;
    private int count;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ShopQueryListBean(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }
}

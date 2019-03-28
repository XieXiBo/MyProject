package com.bwie.mall.bean;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/28 18:09:13
 * @Description:
 */
public class SubmitMenuBean {
    private int commodityId;
    private int amount;

    public SubmitMenuBean(int amount, int commodityId) {
        this.commodityId = commodityId;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SubmitMenuBean{" +
                "commodityId=" + commodityId +
                ", amount=" + amount +
                '}';
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

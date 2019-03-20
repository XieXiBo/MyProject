package com.bwie.mall.api;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 14:46:12
 * @Description:接口类
 */
public class Api {

    public static final String BASE_URL = "http://172.17.8.100/";
    //登录
    public static final String LOGIN_URL = "small/user/v1/login";
    //注册
    public static final String REGIST_URL = "small/user/v1/register";
    //banner
    public static final String BANNER = "small/commodity/v1/bannerShow";
    //首页展示
    public static final String GOODS = "small/commodity/v1/commodityList";
    //搜索商品
    public static final String SEARCH_URL = "small/commodity/v1/findCommodityByKeyword";
    //商品详情
    public static final String DETAILS_URL = "small/commodity/v1/findCommodityDetailsById";
}

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
    //同步(添加)购物车
    public static final String SYNC_SHOPCAR = "small/order/verify/v1/syncShoppingCart";
    //查询
    public static final String QUERY_SHOPCAR = "small/order/verify/v1/findShoppingCart";
    //圈子列表展示
    public static final String CIRCLE_LIST = "small/circle/v1/findCircleList";
    //地址列表
    public static final String ADDRESS_URL = "small/user/verify/v1/receiveAddressList";
    //新增地址
    public static final String INSERT_ADDRESS = "small/user/verify/v1/addReceiveAddress";
    //默认收货地址
    public static final String MR_ADDRESS = "small/user/verify/v1/setDefaultReceiveAddress";
    //创建订单
    public static final String CREATE_MENU = "small/order/verify/v1/createOrder";
    //支付
    public static final String PAY_URL = "small/order/verify/v1/pay";
    //一级分类
    public static final String FIRST_URL = "small/commodity/v1/findFirstCategory";
    //二级分类
    public static final String SECOND_URL = "small/commodity/v1/findSecondCategory";
    //根据二级分类查询子商品
    public static final String FORM_SECOND = "small/commodity/v1/findCommodityByCategory";
    //根据订单状态查询
    public static final String MENU_STATE = "small/order/verify/v1/findOrderListByStatus";
    //根据用户ID查询用户信息
    public static final String MINE_INFO = "small/user/verify/v1/getUserById";
    //查询用户钱包
    public static final String MINE_WALLET= "small/user/verify/v1/findUserWallet";
    //我的足迹
    public static final String MINE_FOOT= "small/commodity/verify/v1/browseList";

}

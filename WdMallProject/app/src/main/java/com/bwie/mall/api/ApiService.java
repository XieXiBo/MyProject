package com.bwie.mall.api;

import com.bwie.mall.bean.BannerBean;
import com.bwie.mall.bean.DetailsBean;
import com.bwie.mall.bean.GoodsBean;
import com.bwie.mall.bean.LoginBean;
import com.bwie.mall.bean.RegistBean;
import com.bwie.mall.bean.SearchBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/18 14:01:45
 * @Description:
 */
public interface ApiService {
    /**
     * 登录
     */
    @POST(Api.LOGIN_URL)
    @FormUrlEncoded
    Flowable<LoginBean> login(@FieldMap Map<String, String> params);

    /**
     * 注册
     */
    @POST(Api.REGIST_URL)
    @FormUrlEncoded
    Flowable<RegistBean> regist(@FieldMap Map<String, String> params);

    /**
     * banner
     */
    @GET(Api.BANNER)
    Flowable<BannerBean> getBanner();

    /**
     * 首页商品展示
     */
    @GET(Api.GOODS)
    Flowable<GoodsBean> getShowGoods();

    /**
     * 搜索商品
     */
    @GET(Api.SEARCH_URL)
    Flowable<SearchBean> getSearch(@Query("keyword") String keyword, @Query("page") int page, @Query("count") int count);

    /**
     * 商品详情
     */
    @GET(Api.DETAILS_URL)
    Flowable<DetailsBean> getDetails(@Query("commodityId") String commodityId);
}

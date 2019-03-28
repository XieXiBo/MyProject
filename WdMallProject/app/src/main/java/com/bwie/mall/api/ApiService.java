package com.bwie.mall.api;

import com.bwie.mall.bean.BannerBean;
import com.bwie.mall.bean.CircleListBean;
import com.bwie.mall.bean.GoodsBean;
import com.bwie.mall.bean.InsertAddressBean;
import com.bwie.mall.bean.LoginBean;
import com.bwie.mall.bean.MineAddressBean;
import com.bwie.mall.bean.MrAddressBean;
import com.bwie.mall.bean.NewMenuBean;
import com.bwie.mall.bean.QueryCartBean;
import com.bwie.mall.bean.RegistBean;
import com.bwie.mall.bean.SearchBean;
import com.bwie.mall.bean.ShopDetails;
import com.bwie.mall.bean.SyncShopCarBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
    Flowable<ShopDetails> getDetails(@Query("commodityId") String commodityId);

    /**
     * 同步购物车
     */
   /* @PUT(Api.SYNC_SHOPCAR)
    Flowable<SyncShopCarBean> getSyncShopCar(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("data") String data);
     */
    @Multipart
    @PUT(Api.SYNC_SHOPCAR)
    Flowable<SyncShopCarBean> getSyncShopCar(@Header("userId") int userId, @Header("sessionId") String sessionId, @Part("data") RequestBody data);

    /**
     * 查询购物车
     */
    @GET(Api.QUERY_SHOPCAR)
    Flowable<QueryCartBean> getQueryCart(@Header("userId") String userId, @Header("sessionId") String sessionId);

    /**
     * 圈子列表
     */
    @GET(Api.CIRCLE_LIST)
    Flowable<CircleListBean> getCircle(@HeaderMap Map<String, String> params, @Query("count") int count, @Query("page") int page);

    /**
     * 查询收货地址列表
     */
    @GET(Api.ADDRESS_URL)
    Flowable<MineAddressBean> getAddress(@Header("userId") String userId, @Header("sessionId") String sessionId);

    /**
     * 新增收货地址
     */
    @POST(Api.INSERT_ADDRESS)
    @FormUrlEncoded
    Flowable<InsertAddressBean> insertAddress(@Header("userId") String userId, @Header("sessionId") String sessionId, @FieldMap Map<String, String> params);

    /**
     * 默认收货地址
     */
    @POST(Api.MR_ADDRESS)
    @FormUrlEncoded
    Flowable<MrAddressBean> setMrAddress(@Header("userId") String userId, @Header("sessionId") String sessionId, @Field("id") int id);


    /**
     * 创建订单
     */
    @POST(Api.CREATE_MENU)
    @FormUrlEncoded
    Flowable<NewMenuBean> createMenu(@Header("userId") int userId, @Header("sessionId") String sessionId, @Field("orderInfo") String orderInfo, @Field("totalPrice") double sumPrice, @Field("addressId") int addressId);

    /**
     * 支付
     */
    @POST(Api.PAY_URL)
    Flowable<SyncShopCarBean> goPay(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("orderId") String orderId,@Query("payType") int payType);
}

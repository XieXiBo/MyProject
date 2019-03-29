package com.bwie.mall.utils;

import android.util.Log;

import com.bwie.mall.api.Api;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 14:42:28
 * @Description:
 */
public class RetrofitUtils {

    /**
     * 单例模式
     */
    private static RetrofitUtils retrofitUtils;

    private RetrofitUtils() {
    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    /**
     * 获取OkHttp的方法
     */
    private static OkHttpClient okHttpClient;

    private static synchronized OkHttpClient getOkHttpClient() {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("lj", "log: " + message);
            }
        });
        //实例okhttp
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(3000, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    /**
     * 获取Retrofit方法
     */
    private static Retrofit retrofit;

    private static synchronized Retrofit getRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)//域名接口
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }

    /**
     * 设置代理对象方法
     */
    public <T> T setCreate(Class<T> apiService) {
        Retrofit myRetrofit = getRetrofit();
        //通过Java动态代理的方式获取动态代理对象
        return myRetrofit.create(apiService);
    }
}

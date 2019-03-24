package com.bwie.mall.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: xiexibo
 * @Date: 2019/3/17 14:16:56
 * @Description:
 */
public abstract class BasePresenter<T> {

    private Reference<T> tReference;


    /**
     * 弱引用控制外部类对象
     *
     * @param t
     */
    public void attachView(T t) {
        tReference = new WeakReference<T>(t);
    }

    public void deatchView() {
        if (tReference != null) {
            tReference.clear();
            tReference = null;
        }
    }
}

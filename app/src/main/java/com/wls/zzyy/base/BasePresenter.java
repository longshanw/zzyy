package com.wls.zzyy.base;

/**
 * Description: BasePresenter
 * Creator: yxc
 * date: 2017/9/21 10:42
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}

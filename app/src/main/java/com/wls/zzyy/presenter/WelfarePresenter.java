package com.wls.zzyy.presenter;

import android.util.DisplayMetrics;

import com.wls.zzyy.app.App;
import com.wls.zzyy.base.RxPresenter;
import com.wls.zzyy.model.bean.GankHttpResponse;
import com.wls.zzyy.model.bean.GankItemBean;
import com.wls.zzyy.model.net.RetrofitHelper;
import com.wls.zzyy.presenter.contract.WelfareContract;
import com.wls.zzyy.utils.RxUtil;
import com.wls.zzyy.utils.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Description:
 * Creator: wls
 * date: $date $time
 */

public class WelfarePresenter extends RxPresenter<WelfareContract.View> implements WelfareContract.Presenter {
    public static final int NUM_OF_PAGE = 20;

    private int currentPage = 1;

    @Inject
    public WelfarePresenter() {
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        Subscription rxSubscription = RetrofitHelper.getGankApis().getGirlList(NUM_OF_PAGE, currentPage)
                .compose(RxUtil.<GankHttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleGankResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> gankItemBeen) {
                        setHeight(gankItemBeen);
                        mView.showContent(gankItemBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("数据加载失败ヽ(≧Д≦)ノ");
                    }
                });
        addSubscribe(rxSubscription);
    }

    @Override
    public void loadMore() {
        Subscription rxSubscription = RetrofitHelper.getGankApis().getGirlList(NUM_OF_PAGE, ++currentPage)
                .compose(RxUtil.<GankHttpResponse<List<GankItemBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<GankItemBean>>handleGankResult())
                .subscribe(new Action1<List<GankItemBean>>() {
                    @Override
                    public void call(List<GankItemBean> gankItemBeen) {
                        setHeight(gankItemBeen);
                        mView.showMoreContent(gankItemBeen);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.showError("加载更多数据失败ヽ(≧Д≦)ノ");
                    }
                });
        addSubscribe(rxSubscription);
    }

    private void setHeight(List<GankItemBean> list) {
        DisplayMetrics dm = App.getInstance().getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels / 2;//宽度为屏幕宽度一半
        for (GankItemBean gankItemBean : list) {
            gankItemBean.setHeight(width * StringUtils.getRandomNumber(3, 6) / 3);//随机的高度
        }
    }

}

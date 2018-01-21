package com.wls.zzyy.presenter;

import com.wls.zzyy.base.RxPresenter;
import com.wls.zzyy.model.bean.VideoRes;
import com.wls.zzyy.model.http.response.VideoHttpResponse;
import com.wls.zzyy.model.net.RetrofitHelper;
import com.wls.zzyy.presenter.contract.RecommendContract;
import com.wls.zzyy.utils.RxUtil;
import com.wls.zzyy.utils.StringUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Description: RecommendPresenter
 * Creator: wls
 * date: 2017/9/21 16:26
 */
public class RecommendPresenter extends RxPresenter<RecommendContract.View> implements RecommendContract.Presenter {

    int page = 0;

    @Inject
    public RecommendPresenter() {}

    @Override
    public void onRefresh() {
        page = 0;
        getPageHomeInfo();
    }

    private void getPageHomeInfo() {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getHomePage()
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(final VideoRes res) {
                        if (res != null) {
                            mView.showContent(res);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }

}

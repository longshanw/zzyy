package com.wls.zzyy.presenter;

import com.wls.zzyy.base.RxPresenter;
import com.wls.zzyy.model.bean.VideoRes;
import com.wls.zzyy.model.exception.ApiException;
import com.wls.zzyy.model.net.HttpMethods;
import com.wls.zzyy.model.net.MyObserver;
import com.wls.zzyy.presenter.contract.ClassificationContract;

import javax.inject.Inject;

/**
 * Description: ClassificationPresenter
 * Creator: wls
 * date: 2017/9/21 17:55
 */
public class ClassificationPresenter extends RxPresenter<ClassificationContract.View> implements ClassificationContract.Presenter {
    int page = 0;

    @Inject
    public ClassificationPresenter() {
    }

    @Override
    public void onRefresh() {
        page = 0;
        getPageHomeInfo();
    }

    private void getPageHomeInfo() {
        HttpMethods.getInstance().queryClassification()
                .subscribe(new MyObserver<VideoRes>() {
                    @Override
                    protected void onError(ApiException ex) {
                        mView.refreshFaild(ex.getDisplayMessage());
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onNext(VideoRes res) {
                        if (res != null) {
                            mView.showContent(res);
                        }
                    }
                });
    }
}

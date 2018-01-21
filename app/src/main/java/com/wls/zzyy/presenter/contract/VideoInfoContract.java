package com.wls.zzyy.presenter.contract;


import com.wls.zzyy.base.BasePresenter;
import com.wls.zzyy.base.BaseView;
import com.wls.zzyy.model.bean.VideoRes;

public interface VideoInfoContract {

    interface View extends BaseView {

        void showContent(VideoRes videoRes);

        void hidLoading();

        void collected();

        void disCollect();
    }

    interface Presenter extends BasePresenter<View> {
        void getDetailData(String dataId);

        void collect();

        void insertRecord();

    }
}

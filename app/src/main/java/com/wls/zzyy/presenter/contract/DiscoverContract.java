package com.wls.zzyy.presenter.contract;


import com.wls.zzyy.base.BasePresenter;
import com.wls.zzyy.base.BaseView;
import com.wls.zzyy.model.bean.VideoRes;

/**
 * Description: RecommendContract
 * Creator: wls
 * date: 2017/9/21 15:53
 */
public interface DiscoverContract {

    interface View extends BaseView {

        void showContent(VideoRes videoRes);

        void refreshFaild(String msg);

        void hidLoading();

        int getLastPage();

        void setLastPage(int page);
    }

    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}

package com.wls.zzyy.presenter.contract;


import com.wls.zzyy.base.BasePresenter;
import com.wls.zzyy.base.BaseView;
import com.wls.zzyy.model.bean.VideoRes;

/**
 * Description: ClassificationContract
 * Creator: wls
 * date: 2017/9/21 17:55
 */
public interface ClassificationContract {

    interface View extends BaseView {

        void showContent(VideoRes videoRes);

        void refreshFaild(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();
    }
}

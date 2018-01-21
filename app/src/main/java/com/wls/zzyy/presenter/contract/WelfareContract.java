package com.wls.zzyy.presenter.contract;

import com.wls.zzyy.base.BasePresenter;
import com.wls.zzyy.base.BaseView;
import com.wls.zzyy.model.bean.GankItemBean;

import java.util.List;

/**
 * Description: WelfareContract
 * Creator: wls
 * date: 2017/10/24 12:34
 */
public interface WelfareContract {
    interface View extends BaseView {


        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showContent(List<GankItemBean> list);

        void showMoreContent(List<GankItemBean> list);
    }

    interface Presenter extends BasePresenter<View> {
        void onRefresh();

        void loadMore();
    }
}

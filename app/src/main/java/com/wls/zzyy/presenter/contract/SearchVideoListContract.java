package com.wls.zzyy.presenter.contract;


import com.wls.zzyy.base.BasePresenter;
import com.wls.zzyy.base.BaseView;
import com.wls.zzyy.model.bean.VideoInfo;
import com.wls.zzyy.model.bean.VideoType;

import java.util.List;

/**
 * Description: VideoListContract
 * Creator: yxc
 * date: 2017/9/16 14:59
 */
public interface SearchVideoListContract {

    interface View extends BaseView {

        void refreshFaild(String msg);

        void loadMoreFaild(String msg);

        void showContent(List<VideoType> list);

        void showMoreContent(List<VideoType> list);

        void showRecommend(List<VideoInfo> list);
    }

    interface Presenter extends BasePresenter<View> {

        void onRefresh();

        void loadMore();

        void setSearchKey(String strSearchKey);

    }
}

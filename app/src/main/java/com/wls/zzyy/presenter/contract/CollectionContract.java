package com.wls.zzyy.presenter.contract;


import com.wls.zzyy.base.BasePresenter;
import com.wls.zzyy.base.BaseView;
import com.wls.zzyy.model.bean.VideoType;

import java.util.List;

/**
 * Description: CollectionContract
 * Creator: yxc
 * date: 2016/9/29 12:06
 */
public interface CollectionContract {

    interface View extends BaseView {


        void showContent(List<VideoType> list);

    }

    interface Presenter extends BasePresenter<View> {
        void getData(int type);

        void getCollectData();

        void delAllDatas();

        void getRecordData();

        int getType();
    }
}

package com.wls.zzyy.presenter;

import com.wls.zzyy.base.RxPresenter;
import com.wls.zzyy.model.bean.Collection;
import com.wls.zzyy.model.bean.Record;
import com.wls.zzyy.model.bean.VideoType;
import com.wls.zzyy.model.db.RealmHelper;
import com.wls.zzyy.presenter.contract.CollectionContract;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description: CollectionPresenter
 * Creator: wls
 * date: 2017/9/29 12:15
 */
public class CollectionPresenter extends RxPresenter<CollectionContract.View> implements CollectionContract.Presenter {
    int type = 0;//收藏:0; 历史:1:

    @Inject
    public CollectionPresenter() {
    }

    @Override
    public void getData(int type) {
        this.type = type;
        if (type == 0) {
            getCollectData();
        } else {
            getRecordData();
        }
    }

    @Override
    public void getCollectData() {
        List<Collection> collections = RealmHelper.getInstance().getCollectionList();
        List<VideoType> list = new ArrayList<>();
        VideoType videoType;
        for (Collection collection : collections) {
            videoType = new VideoType();
            videoType.title = collection.title;
            videoType.pic = collection.pic;
            videoType.dataId = collection.getId();
            videoType.score = collection.getScore();
            videoType.airTime = collection.getAirTime();
            list.add(videoType);
        }
        mView.showContent(list);
    }

    @Override
    public void delAllDatas() {
        if (type == 0) {
            RealmHelper.getInstance().deleteAllCollection();
        } else {
            RealmHelper.getInstance().deleteAllRecord();
            EventBus.getDefault().post("", VideoInfoPresenter.Refresh_History_List);
        }
    }

    @Override
    public void getRecordData() {
        List<Record> records = RealmHelper.getInstance().getRecordList();
        List<VideoType> list = new ArrayList<>();
        VideoType videoType;
        for (Record record : records) {
            videoType = new VideoType();
            videoType.title = record.title;
            videoType.pic = record.pic;
            videoType.dataId = record.getId();
            list.add(videoType);
        }
        mView.showContent(list);
    }

    @Override
    public int getType() {
        return type;
    }
}

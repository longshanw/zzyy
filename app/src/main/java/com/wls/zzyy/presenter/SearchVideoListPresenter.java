package com.wls.zzyy.presenter;

import com.wls.zzyy.base.RxPresenter;
import com.wls.zzyy.model.bean.VideoRes;
import com.wls.zzyy.model.http.response.VideoHttpResponse;
import com.wls.zzyy.model.net.RetrofitHelper;
import com.wls.zzyy.presenter.contract.SearchVideoListContract;
import com.wls.zzyy.utils.RxUtil;
import com.wls.zzyy.utils.StringUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;

public class SearchVideoListPresenter extends RxPresenter<SearchVideoListContract.View> implements SearchVideoListContract.Presenter {
    int page = 1;

    String searchStr = "";

    @Inject
    public SearchVideoListPresenter() {
    }

    @Override
    public void onRefresh() {
        page = 1;
        if (searchStr != null && !searchStr.equals("")) {
            getSearchVideoList(searchStr);
        }
    }

    @Override
    public void loadMore() {
        page++;
        if (searchStr != null && !searchStr.equals("")) {
            getSearchVideoList(searchStr);
        }
    }

    @Override
    public void setSearchKey(String strSearchKey) {
        this.searchStr = strSearchKey;
    }

    /**
     * 搜索电影
     *
     * @param searchStr
     */
    private void getSearchVideoList(String searchStr) {
        Subscription rxSubscription = RetrofitHelper.getVideoApi().getVideoListByKeyWord(searchStr, page + "")
                .compose(RxUtil.<VideoHttpResponse<VideoRes>>rxSchedulerHelper())
                .compose(RxUtil.<VideoRes>handleResult())
                .subscribe(new Action1<VideoRes>() {
                    @Override
                    public void call(VideoRes res) {
                        if (res != null) {
                            if (page == 1) {
                                mView.showContent(res.list);
                            } else {
                                mView.showMoreContent(res.list);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if (page > 1) {
                            page--;
                        }
                        mView.refreshFaild(StringUtils.getErrorMsg(throwable.getMessage()));
                    }
                });
        addSubscribe(rxSubscription);
    }


}

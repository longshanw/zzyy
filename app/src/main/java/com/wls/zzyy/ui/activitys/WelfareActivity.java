package com.wls.zzyy.ui.activitys;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.wls.zzyy.R;
import com.wls.zzyy.base.SwipeBackActivity;
import com.wls.zzyy.model.bean.GankItemBean;
import com.wls.zzyy.presenter.WelfarePresenter;
import com.wls.zzyy.presenter.contract.WelfareContract;
import com.wls.zzyy.ui.adapter.WelfareAdapter;
import com.wls.zzyy.utils.EventUtil;
import com.wls.zzyy.utils.ScreenUtil;
import com.wls.zzyy.widget.theme.ColorTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 福利墙
 * Creator: wls
 * date: 2017/9/6 14:57
 */
public class WelfareActivity extends SwipeBackActivity<WelfarePresenter> implements WelfareContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {
    @BindView(R.id.title_name)
    ColorTextView titleName;
    @BindView(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;

    WelfareAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_welfare;
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    protected void initView() {
        titleName.setText("福利");
        mRecyclerView.setAdapterWithProgress(mAdapter = new WelfareAdapter(mContext));
        mRecyclerView.setErrorView(R.layout.view_error);
        mAdapter.setMore(R.layout.view_more, this);
        mAdapter.setNoMore(R.layout.view_nomore);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpaceDecoration itemDecoration = new SpaceDecoration(ScreenUtil.dip2px(mContext, 8));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        mRecyclerView.addItemDecoration(itemDecoration);
        onRefresh();
    }

    @Override
    protected void initEvent() {
        mRecyclerView.setRefreshListener(this);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        mAdapter.setError(R.layout.view_error_footer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.resumeMore();
            }
        });
        mRecyclerView.getErrorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.showProgress();
                onRefresh();
            }
        });
    }


    @Override
    public void refreshFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        mRecyclerView.showError();
    }

    @Override
    public void loadMoreFaild(String msg) {
        if (!TextUtils.isEmpty(msg))
            showError(msg);
        mAdapter.pauseMore();
    }

    public void clearFooter() {
        mAdapter.setMore(new View(mContext), this);
        mAdapter.setError(new View(mContext));
        mAdapter.setNoMore(new View(mContext));
    }

    @Override
    public void showContent(List<GankItemBean> list) {
        mAdapter.clear();
        if (list != null && list.size() < WelfarePresenter.NUM_OF_PAGE) {
            clearFooter();
        }
        mAdapter.addAll(list);
    }

    @Override
    public void showMoreContent(List<GankItemBean> list) {
        mAdapter.addAll(list);
    }

    @OnClick(R.id.rl_back)
    public void onClick() {
        if (mContext instanceof WelfareActivity) {
            finish();
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void onLoadMore() {
        mPresenter.loadMore();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}

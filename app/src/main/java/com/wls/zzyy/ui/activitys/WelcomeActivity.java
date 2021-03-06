package com.wls.zzyy.ui.activitys;

import android.widget.ImageView;

import com.wls.zzyy.R;
import com.wls.zzyy.base.BaseMvpActivity;
import com.wls.zzyy.component.ImageLoader;
import com.wls.zzyy.presenter.WelcomePresenter;
import com.wls.zzyy.presenter.contract.WelcomeContract;
import com.wls.zzyy.utils.EventUtil;
import com.wls.zzyy.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
/**
 * Description: 开屏页
 * Creator: wls
 * date: 2017/9/6 14:57
 */
public class WelcomeActivity extends BaseMvpActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        mPresenter.getWelcomeData();
    }

    @Override
    public void showError(String msg) {
        EventUtil.showToast(mContext, msg);
    }

    @Override
    public void showContent(List<String> list) {
        if (list != null) {
            int page = StringUtils.getRandomNumber(0, list.size() - 1);
            ImageLoader.load(mContext, list.get(page), ivWelcomeBg);
            ivWelcomeBg.animate().scaleX(1.12f).scaleY(1.12f).setDuration(2000).setStartDelay(100).start();
        }

    }

    @Override
    public void jumpToMain() {
        MainActivity.start(this);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }
}
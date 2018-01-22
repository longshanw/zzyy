package com.wls.zzyy.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.color.ColorChooserDialog;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.pgyersdk.feedback.PgyFeedback;
import com.pgyersdk.views.PgyerDialog;
import com.wls.zzyy.R;
import com.wls.zzyy.app.App;
import com.wls.zzyy.app.Constants;
import com.wls.zzyy.base.BaseActivity;
import com.wls.zzyy.ui.adapter.ContentPagerAdapter;
import com.wls.zzyy.ui.fragments.ClassificationFragment;
import com.wls.zzyy.ui.fragments.DiscoverFragment;
import com.wls.zzyy.ui.fragments.MineFragment;
import com.wls.zzyy.ui.fragments.RecommendFragment;
import com.wls.zzyy.utils.EventUtil;
import com.wls.zzyy.utils.PreUtils;
import com.wls.zzyy.utils.StringUtils;
import com.wls.zzyy.utils.ThemeUtil;
import com.wls.zzyy.utils.ThemeUtils;
import com.wls.zzyy.widget.ResideLayout;
import com.wls.zzyy.widget.UnScrollViewPager;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description: 主页
 * Creator: wls
 * date: 2017/9/6 14:57
 * ColorChooserDialog.ColorCallback：主题选择
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ColorChooserDialog.ColorCallback {

    public static final String Set_Theme_Color = "Set_Theme_Color";
    public final static String Banner_Stop = "Banner_Stop";
    private Long firstTime = 0L;
    final int WAIT_TIME = 200;
    //菜单（收藏）
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    //菜单（下载）
    @BindView(R.id.tv_mydown)
    TextView tvMydown;
    //菜单（福利）
    @BindView(R.id.tv_fuli)
    TextView tvFuli;
    //菜单（分享）
    @BindView(R.id.tv_share)
    TextView tvShare;
    //菜单（建议反馈）
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    //菜单（设置）
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    //菜单（关于）
    @BindView(R.id.about)
    TextView about;
    //菜单（主题）
    @BindView(R.id.theme)
    TextView theme;
    //导航栏（影视/音乐/图片/头条/阅读...）
    @BindView(R.id.tab_rg_menu)
    RadioGroup tabRgMenu;
    //内容
    @BindView(R.id.vp_content)
    UnScrollViewPager vpContent;
    //带3D感的侧滑菜单
    @BindView(R.id.resideLayout)
    ResideLayout mResideLayout;
    //导航栏tab切换
    ContentPagerAdapter mPagerAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        List<Fragment> fragments = initFragments();
        vpContent.setScrollable(false);
        mPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager(), fragments);
        vpContent.setAdapter(mPagerAdapter);
        vpContent.setOffscreenPageLimit(fragments.size());
        //菜单（收藏）
        StringUtils.setIconDrawable(mContext, tvCollect, MaterialDesignIconic.Icon.gmi_collection_bookmark, 16, 10);
        //菜单（下载）
        StringUtils.setIconDrawable(mContext, tvMydown, MaterialDesignIconic.Icon.gmi_download, 16, 10);
        //菜单（福利）
        StringUtils.setIconDrawable(mContext, tvFuli, MaterialDesignIconic.Icon.gmi_mood, 16, 10);
        //菜单（分享）
        StringUtils.setIconDrawable(mContext, tvShare, MaterialDesignIconic.Icon.gmi_share, 16, 10);
        //菜单（建议反馈）
        StringUtils.setIconDrawable(mContext, tvFeedback, MaterialDesignIconic.Icon.gmi_android, 16, 10);
        //菜单（设置）
        StringUtils.setIconDrawable(mContext, tvSetting, MaterialDesignIconic.Icon.gmi_settings, 16, 10);
        //菜单（关于）
        StringUtils.setIconDrawable(mContext, about, MaterialDesignIconic.Icon.gmi_account, 16, 10);
        //菜单（主题）
        StringUtils.setIconDrawable(mContext, theme, MaterialDesignIconic.Icon.gmi_palette, 16, 10);
    }

    @Override
    protected void initEvent() {
        tabRgMenu.setOnCheckedChangeListener(this);
        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) tabRgMenu.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mResideLayout.setPanelSlideListener(new ResideLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                postBannerState(true);
            }

            @Override
            public void onPanelOpened(View panel) {
                postBannerState(true);
            }

            @Override
            public void onPanelClosed(View panel) {
                postBannerState(false);
            }
        });
    }

    private void postBannerState(final boolean stop) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(stop, Banner_Stop);
            }
        }, WAIT_TIME);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.tab_rb_1:
                vpContent.setCurrentItem(0, false);
                break;
            case R.id.tab_rb_2:
                vpContent.setCurrentItem(1, false);
                break;
            case R.id.tab_rb_3:
                vpContent.setCurrentItem(2, false);
                break;
            case R.id.tab_rb_4:
                vpContent.setCurrentItem(3, false);
                break;
        }
    }

    private List<Fragment> initFragments() {
        List<Fragment> fragments = new ArrayList<>();
        Fragment fragment1 = new RecommendFragment();
        Fragment fragment2 = new ClassificationFragment();
        Fragment fragment3 = new DiscoverFragment();
        Fragment mineFragment = new MineFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(mineFragment);
        return fragments;
    }

    @Subscriber(tag = MineFragment.SET_THEME)
    public void setTheme(String content) {
        new ColorChooserDialog.Builder(this, R.string.theme)
                .customColors(R.array.colors, null)
                .doneButton(R.string.done)
                .cancelButton(R.string.cancel)
                .allowUserColorInput(false)
                .allowUserColorInputAlpha(false)
                .show();
    }

    @OnClick({R.id.tv_collect, R.id.tv_mydown, R.id.tv_fuli, R.id.tv_share, R.id.tv_feedback, R.id.tv_setting, R.id.about, R.id.theme})
    public void onClick(View view) {
        switch (view.getId()) {
            //菜单（收藏）
            case R.id.tv_collect:
                mContext.startActivity(new Intent(mContext, CollectionActivity.class));
                break;
            //菜单（下载）
            case R.id.tv_mydown:
                EventUtil.showToast(mContext, "敬请期待");
                break;
            //菜单（福利）
            case R.id.tv_fuli:
                mContext.startActivity(new Intent(mContext, WelfareActivity.class));
                break;
            //菜单（分享）
            case R.id.tv_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.setting_recommend_content));
                shareIntent.setType("text/plain");

                //设置分享列表的标题，并且每次都显示分享列表
                mContext.startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
            //菜单（建议反馈）
            case R.id.tv_feedback:
                // 以对话框的形式弹出
                PgyerDialog.setDialogTitleBackgroundColor(PreUtils.getString(mContext, Constants.PRIMARYCOLOR, "#000000"));
                PgyerDialog.setDialogTitleTextColor(PreUtils.getString(mContext, Constants.TITLECOLOR, "#0aa485"));
                PgyFeedback.getInstance().showDialog(mContext).d().setChecked(false);
                break;
            //菜单（设置）
            case R.id.tv_setting:
                mContext.startActivity(new Intent(mContext, SettingActivity.class));
                break;
            //菜单（关于）
            case R.id.about:
                new MaterialDialog.Builder(mContext)
                        .title(R.string.about)
                        .titleColor(ThemeUtils.getThemeColor(mContext, R.attr.colorPrimary))
                        .icon(new IconicsDrawable(mContext)
                                .color(ThemeUtils.getThemeColor(mContext, R.attr.colorPrimary))
                                .icon(MaterialDesignIconic.Icon.gmi_account)
                                .sizeDp(20))
                        .content(R.string.about_me)
                        .contentColor(ThemeUtils.getThemeColor(mContext, R.attr.colorPrimary))
                        .positiveText(R.string.close)
                        .show();
                break;
            //菜单（主题）
            case R.id.theme:
                setTheme("");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onColorSelection(ColorChooserDialog dialog, int selectedColor) {
        ThemeUtil.onColorSelection(this, dialog, selectedColor);
        EventBus.getDefault().post("", Set_Theme_Color);
    }

    @Override
    public void onBackPressed() {
        if (mResideLayout.isOpen()) {
            mResideLayout.closePane();
        } else {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1500) {
                EventUtil.showToast(this, "再按一次退出");
                firstTime = secondTime;
            } else {
                App.getInstance().exitApp();
            }
        }
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

}
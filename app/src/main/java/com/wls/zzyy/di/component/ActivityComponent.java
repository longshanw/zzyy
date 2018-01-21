package com.wls.zzyy.di.component;

import android.app.Activity;

import com.wls.zzyy.di.module.ActivityModule;
import com.wls.zzyy.di.scope.ActivityScope;
import com.wls.zzyy.ui.activitys.CollectionActivity;
import com.wls.zzyy.ui.activitys.HistoryActivity;
import com.wls.zzyy.ui.activitys.SearchActivity;
import com.wls.zzyy.ui.activitys.VideoInfoActivity;
import com.wls.zzyy.ui.activitys.VideoListActivity;
import com.wls.zzyy.ui.activitys.WelcomeActivity;
import com.wls.zzyy.ui.activitys.WelfareActivity;

import dagger.Component;

/**
 * Description:
 * Creator: wls
 * date: $date $time
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity getActivity();

    void inject(VideoInfoActivity videoInfoActivity);

    void inject(WelcomeActivity welcomeActivity);

    void inject(CollectionActivity collectionActivity);

    void inject(HistoryActivity historyActivity);

    void inject(SearchActivity searchActivity);

    void inject(VideoListActivity videoListActivity);

    void inject(WelfareActivity welfareActivity);

}

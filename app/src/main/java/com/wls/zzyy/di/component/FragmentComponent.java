package com.wls.zzyy.di.component;

import android.app.Activity;

import com.wls.zzyy.di.module.FragmentModule;
import com.wls.zzyy.di.scope.FragmentScope;
import com.wls.zzyy.ui.fragments.CommentFragment;
import com.wls.zzyy.ui.fragments.MineFragment;
import com.wls.zzyy.ui.fragments.RecommendFragment;
import com.wls.zzyy.ui.fragments.ClassificationFragment;
import com.wls.zzyy.ui.fragments.DiscoverFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DiscoverFragment dailyFragment);

    void inject(ClassificationFragment dailyFragment);

    void inject(RecommendFragment recommendFragment);

    void inject(MineFragment mineFragment);

    void inject(CommentFragment commentFragment);

//    void inject(VideoIntroFragment videoIntroFragment);

}

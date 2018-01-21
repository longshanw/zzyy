package com.wls.zzyy.di.component;


import com.wls.zzyy.app.App;
import com.wls.zzyy.di.module.AppModule;
import com.wls.zzyy.di.module.HttpModule;
import com.wls.zzyy.model.DataManager;
import com.wls.zzyy.model.db.RealmHelper;
import com.wls.zzyy.model.http.RetrofitHelper1;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper1 retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

}

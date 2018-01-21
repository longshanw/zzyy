package com.wls.zzyy.di.module;


import com.wls.zzyy.app.App;
import com.wls.zzyy.model.DataManager;
import com.wls.zzyy.model.db.DBHelper;
import com.wls.zzyy.model.db.RealmHelper;
import com.wls.zzyy.model.http.HttpHelper;
import com.wls.zzyy.model.http.RetrofitHelper1;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by codeest on 16/8/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper1 retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }


    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper) {
        return new DataManager(httpHelper, DBHelper);
    }
}

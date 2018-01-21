package com.wls.zzyy.widget.theme;

import android.content.res.Resources;
import android.view.View;

/**
 * 换肤接口
 * Created by wls on 17/6/8.
 */
public interface ColorUiInterface {


    View getView();

    void setTheme(Resources.Theme themeId);
}

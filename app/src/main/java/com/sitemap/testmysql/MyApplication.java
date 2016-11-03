package com.sitemap.testmysql;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2016/10/28.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initXutils();
    }

    /**
     * 初始化xutils框架
     */
    private void initXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

}

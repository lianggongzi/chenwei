package com.example.administrator.chenwei.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018-07-05.
 */
public class App extends Application {
    private static App instance;
    private Set<Activity> allActivities;
    Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        instance = this;
    }
    //同步
    public static synchronized App getInstance() {
        if (instance == null){
            instance = new App();
        }
        return instance;
    }
    /**
     * 添加activity
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 移除activity
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 退出app
     */
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}

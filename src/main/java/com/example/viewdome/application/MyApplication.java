package com.example.viewdome.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by TianHongChun on 2016/5/9.
 */
public class MyApplication extends Application {

    private List<Activity> mList = new LinkedList<Activity>();

    private static MyApplication application;
    public static MyApplication  getInstance(){
            return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    /**
     * 关闭除当前activity外的所有activity
     * @param activity
     */
    public void finshAllOther(Activity activity) {
        try {
            for (Activity acti : mList) {
                if (acti != activity) {
                    acti.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
        }
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }


}

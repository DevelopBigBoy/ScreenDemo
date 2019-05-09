package com.qianyu.screendemo;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by G400 on 2018/11/27.
 * 功能：提示工具类
 * 作者：ljl
 */

public class ToastUtils {

    public static void show(Activity  mActivity,String msg) {
        Toast.makeText(mActivity,msg,Toast.LENGTH_SHORT).show();
    }
}

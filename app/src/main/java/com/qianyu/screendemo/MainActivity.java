package com.qianyu.screendemo;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button saveImage,toCustomImage;
    private TextView saveImagePath;
    private EditText saveImageContent;
    String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            new PermissionUtils(this).needPermission(200);
        }
        initView();
        initEvent();
    }

    private void initEvent() {
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagePath = ScreenUtil.viewSaveToImage(saveImageContent);
                saveImagePath.setText(imagePath);
            }
        });
        toCustomImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this,PaintViewActivity.class);
              startActivity(intent);
            }
        });

    }

    private void initView() {
        saveImage = findViewById(R.id.saveImage);
        saveImagePath = findViewById(R.id.saveImagePath);
        saveImageContent = findViewById(R.id.saveImageContent);
        toCustomImage= findViewById(R.id.toCustomImage);
        //设置控件宽高
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        layoutView(saveImageContent, width, height);
    }

    private void layoutView(View v, int width, int height) {
        // 整个View的大小 参数是左上角 和右下角的坐标
        v.layout(0, 0, width, height);
        int measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
        int measuredHeight = View.MeasureSpec.makeMeasureSpec(10000, View.MeasureSpec.AT_MOST);
        /** 当然，measure完后，并不会实际改变View的尺寸，需要调用View.layout方法去进行布局。
         * 按示例调用layout函数后，View的大小将会变成你想要设置成的大小。
         */
        v.measure(measuredWidth, measuredHeight);
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
    }

}
package com.qianyu.screendemo;

import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PaintViewActivity extends AppCompatActivity {
    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_view);
        initView();
        initMenu();
    }

    /**
     * 初始化
     */
    private void initView() {
        paintView = (PaintView) findViewById(R.id.activity_paint_pv);
    }

    /**
     * 初始化底部菜单
     */
    private void initMenu() {
        //撤销
        menuItemSelected(R.id.activity_paint_undo, new MenuSelectedListener() {
            @Override
            public void onMenuSelected() {
                ToastUtils.show(PaintViewActivity.this, "撤销");
                paintView.undo();
            }
        });
        //恢复
        menuItemSelected(R.id.activity_paint_redo, new MenuSelectedListener() {
            @Override
            public void onMenuSelected() {
                ToastUtils.show(PaintViewActivity.this, "恢复");
                paintView.redo();
            }
        });

        //颜色
        menuItemSelected(R.id.activity_paint_color, new MenuSelectedListener() {
            @Override
            public void onMenuSelected() {
                ToastUtils.show(PaintViewActivity.this, "红色");
                paintView.setPaintColor(Color.RED);
            }
        });
        //清空
        menuItemSelected(R.id.activity_paint_clear, new MenuSelectedListener() {
            @Override
            public void onMenuSelected() {
                ToastUtils.show(PaintViewActivity.this, "清空");
                paintView.clearAll();
            }
        });

        //橡皮擦
        menuItemSelected(R.id.activity_paint_eraser, new MenuSelectedListener() {
            @Override
            public void onMenuSelected() {
                ToastUtils.show(PaintViewActivity.this, "橡皮擦");
                paintView.setEraserModel(true);
            }
        });

        //保存
        menuItemSelected(R.id.activity_paint_save, new MenuSelectedListener() {
            @Override
            public void onMenuSelected() {
                // 判断手机设备是否有SD卡
                boolean isHasSDCard = Environment.getExternalStorageState().equals(
                        android.os.Environment.MEDIA_MOUNTED);
                if (isHasSDCard) {
                    // SD卡根目录
                    String path = Environment.getExternalStorageDirectory().getPath();
                    String imgName = "paint.jpg";
                    if (paintView.saveImg(path, imgName)) {
                        ToastUtils.show(PaintViewActivity.this, "保存成功");
                    }
                }
            }
        });
    }

    /**
     * 选中底部 Menu 菜单项
     */
    private void menuItemSelected(int viewId, final MenuSelectedListener listener) {
        findViewById(viewId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMenuSelected();
            }
        });

    }


    interface MenuSelectedListener {
        void onMenuSelected();
    }

}

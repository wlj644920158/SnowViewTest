package com.wlj.snowviewtest.download;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DownLoadView extends View {

    private Paint paint;

    public DownLoadView(Context context) {
        this(context, null);
    }

    public DownLoadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownLoadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String downloadString = "下载中50%";


    }
}

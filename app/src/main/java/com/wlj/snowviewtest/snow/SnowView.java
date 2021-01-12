package com.wlj.snowviewtest.snow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SnowView extends View {

    private Paint paint;
    private List<Snow> snows;
    private int snowNum = 100;

    public SnowView(Context context) {
        this(context, null);
    }

    public SnowView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        snows = new ArrayList<>();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        snows.clear();
        for (int i = 0; i < snowNum; i++) {
            snows.add(Snow.generateSnow(w, h));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < snowNum; i++) {
            snows.get(i).onDraw(canvas, paint);
            snows.get(i).step();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        },10);

    }
}

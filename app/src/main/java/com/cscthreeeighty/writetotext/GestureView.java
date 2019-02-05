package com.cscthreeeighty.writetotext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GestureView extends View {
    boolean mShowText;
    int mTextPos;
    private final int paintColor = Color.BLUE;
    private Paint drawPaint;
    private Path path = new Path();

    public GestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GestureView,
                0, 0);

        try {
            mShowText = a.getBoolean(R.styleable.GestureView_showText, false);
            mTextPos = a.getInt(R.styleable.GestureView_labelPosition, 0);
        } finally {
            a.recycle();
        }

        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(25);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        drawPaint.setAlpha(100);

    }

    public boolean isShowText() {
        return mShowText;
    }

    public void setShowText(boolean showText) {
        mShowText = showText;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        float xpad = (float)(getPaddingLeft() + getPaddingRight());
        float ypad = (float)(getPaddingTop() + getPaddingBottom());

        float ww = (float)w - xpad;
        float hh = (float)h - ypad;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float pointX = event.getX();
        float pointY = event.getY();
        switch(action){
            case (MotionEvent.ACTION_DOWN) :
                path.moveTo(pointX, pointY);
                break;
            case (MotionEvent.ACTION_MOVE) :
                path.lineTo(event.getX(), event.getY());
                break;
            case (MotionEvent.ACTION_UP) :
                break;
            default:
                return super.onTouchEvent(event);
        }
        postInvalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, drawPaint);
        Path px = new Path();
        ArrayList<Float> fx = new ArrayList<Float>();
        ArrayList<Float> fy = new ArrayList<Float>();
        ArrayList<Double> x = new ArrayList<Double>();
        x.addAll(Arrays.asList(2351.3, 2353.1, 2355.3, 2357.2, 2359.4, 2363.2, 2365.6, 2368.0, 2370.3, 2372.4, 2374.4, 2376.3, 2378.5, 2380.1, 2383.6, 2384.2, 2381.4, 2378.1, 2373.1, 2367.3, 2359.7, 2352.2, 2344.8, 2340.0, 2335.0, 2331.7, 2328.9, 2327.3, 2327.7, 2328.7, 2331.5, 2334.9, 2338.9, 2343.4, 2349.7, 2369.4, 2372.4, 2375.0));
        ArrayList<Double> y = new ArrayList<Double>();
        y.addAll(Arrays.asList(249.9, 246.7, 244.1, 240.7, 239.2, 238.3, 238.8, 239.8, 241.7, 244.1, 247.3, 250.9, 255.3, 259.7, 271.9, 281.9, 290.8, 299.3, 306.2, 310.4, 311.7, 310.9, 307.6, 303.1, 297.8, 292.2, 286.0, 278.9, 271.7, 265.0, 259.1, 253.9, 249.6, 245.7, 242.9, 241.2, 242.7, 244.3));

        for(Double d : x) {
            fx.add(Float.parseFloat(d.toString()));
        }
        for(Double d : y) {
            fy.add(Float.parseFloat(d.toString()));
        }

        for(int i = 0 ; i < fx.size(); i++) {
            if (i == 0) {
                px.moveTo(fy.get(i), fx.get(i));
            } else {
                px.lineTo(fy.get(i), fx.get(i));
            }
        }

        canvas.drawPath(px, drawPaint);
    }
}

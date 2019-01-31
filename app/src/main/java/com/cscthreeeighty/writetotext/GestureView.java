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
    }
}

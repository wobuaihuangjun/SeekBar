package com.huangzj.seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 进度选择控件，自定义view实现
 * Created by huangzj on 2015/12/30.
 */
public class SeekBarView extends View {

    private Context context;
    private Paint paint;

    private boolean hasInit = false;
    private boolean isDrawBackground;

    private int paddingLeft;

    private int paddingRight;

    private int mViewHeight;
    private int mViewWidth;

    /**
     * 最小进度值
     */
    private int minValue = 0;
    private String minText = "0";
    private float minTextWidth;
    /**
     * 最大进度值
     */
    private int maxValue = 100;
    private String maxText = "100";
    private float maxTextWidth;

    /**
     * 当前进度
     */
    private int currentValue;
    /**
     * 绘制进度条的基线
     */
    private float baseLine;

    public SeekBarView(Context context) {
        super(context);
        init(context);
    }

    public SeekBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        initPaint();
        setBackground(null);
    }

    private void initPaint() {
        paint = new Paint();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(getResources().getColor(R.color.gray));
        paint.setTextSize(Utils.spTopx(context, 18));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        mViewHeight = getMeasuredHeight();
//        mViewWidth = getMeasuredWidth();

        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();

        hasInit = true;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isDrawBackground) {
            setBackground(null);
        } else if (hasInit) { // 初始化完成后在绘制
            drawProgress(canvas);
        }

    }

    private float seekBarHeight = 6;
    private int padding = 4;

    private void drawProgress(Canvas canvas) {
        paint.setColor(Color.RED);// 设置红色
        RectF oval3 = new RectF(paddingLeft + minTextWidth + padding, baseLine + seekBarHeight / 2,
                200, 300);// 设置个新的长方形
        canvas.drawRoundRect(oval3, 20, 15, paint);//第二个参数是x半径，第三个参数是y半径
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                doDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                doMove(event);
                break;
            case MotionEvent.ACTION_UP:
                doUp();
                break;
            default:
                break;
        }
        return true;
    }

    private void doUp() {
    }

    private void doMove(MotionEvent event) {
    }

    private void doDown(MotionEvent event) {
    }

    private float totalProgress;

    @Override
    public void setBackground(Drawable background) {
        background = new Drawable() {
            @Override
            public void draw(Canvas canvas) {
                mViewHeight = getHeight();
                int width = getWidth();

                maxTextWidth = paint.measureText(maxText);
                minTextWidth = paint.measureText(minText);

                Paint.FontMetricsInt fmi = paint.getFontMetricsInt();
                baseLine = (float) (mViewWidth / 2.0 - (fmi.bottom / 2.0 + fmi.top / 2.0));
                // 绘制首尾的标注文字
                canvas.drawText(minText, paddingLeft + (float) (minTextWidth / 2.0), baseLine, paint);
                canvas.drawText(maxText, width - (float) (maxTextWidth / 2.0) - paddingRight, baseLine, paint);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return 0;
            }
        };
        super.setBackground(background);
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
        minText = String.valueOf(minValue);
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        maxText = String.valueOf(maxValue);
        resetBackground();
    }

    private void resetBackground() {
        isDrawBackground = true;
        invalidate();
    }
}

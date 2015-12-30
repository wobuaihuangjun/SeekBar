package com.huangzj.seekbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * 进度选择控件
 * Created by huangzj on 2015/12/28.
 */
public class SeekBar extends android.widget.SeekBar {

    private int oldPaddingTop;

    private int oldPaddingLeft;

    private int oldPaddingRight;

    private int oldPaddingBottom;

    private boolean isSetPadding = true;

    private String mText;

    private float mTextWidth;

    private float mImgWidth;

    private float mImgHei;

    private Paint mPaint;

    private Resources res;

    private Bitmap bm;

    private int textSize = 14;

    private int textPaddingLeft;

    private int textPaddingTop;

    private int imagePaddingLeft;

    private int imagePaddingTop;

    private boolean isHide = true;

    public SeekBar(Context context) {
        super(context);
        init();
    }

    public SeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        if (isSetPadding) {
            super.setPadding(left, top, right, bottom);
        }
    }

    private void init() {
        res = getResources();
        initBitmap();
        initDraw();
        setPadding();
        setOnSeekBarChangeListener(new onSeekBarChangeListener());
    }

    private void initDraw() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setTextSize(textSize);
        mPaint.setColor(Color.argb(255, 85, 85, 85));
    }

    private void initBitmap() {
        bm = BitmapFactory.decodeResource(res, R.drawable.shool_adjust_button);
        if (bm != null) {
            mImgWidth = bm.getWidth();
            mImgHei = bm.getHeight();
        } else {
            mImgWidth = 0;
            mImgHei = 0;
        }
    }

    private class onSeekBarChangeListener implements OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(android.widget.SeekBar seekBar, int progress,
                                      boolean fromUser) {
        }

        @Override
        public void onStartTrackingTouch(android.widget.SeekBar seekBar) {
            setHide(true);
        }

        @Override
        public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
            setHide(true);
        }

    }

    ;

    protected synchronized void onDraw(Canvas canvas) {

        try {
            super.onDraw(canvas);
            if (isHide) {
                int progress = getProgress();

                Rect bounds = this.getProgressDrawable().getBounds();
                float xImg = bounds.width() * getProgress() / getMax()
                        + imagePaddingLeft + oldPaddingLeft;
                float yImg = imagePaddingTop + oldPaddingTop;
                canvas.drawBitmap(bm, xImg, yImg, mPaint);

                if (myDrawListener != null) {
                    float centerX = xImg + mImgWidth / 2;
                    float centerY = yImg + mImgHei / 2;
                    myDrawListener.drawText(centerX, centerY, progress);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnDrawListener(OnDrawListener listener) {
        myDrawListener = listener;
    }

    OnDrawListener myDrawListener;

    public interface OnDrawListener {
        void drawText(float centerX, float centerY, int progress);
    }

    private void setPadding() {
        int top = oldPaddingTop;
        int left = getBitmapWidth() / 2 + oldPaddingLeft;
        int right = getBitmapWidth() / 2 + oldPaddingRight;
        int bottom = oldPaddingBottom;
        isSetPadding = true;
        setPadding(left, top, right, bottom);
        isSetPadding = false;
    }

    public void setBitmap(int resId) {
        bm = BitmapFactory.decodeResource(res, resId);
        if (bm != null) {
            mImgWidth = bm.getWidth();
            mImgHei = bm.getHeight();
        } else {
            mImgWidth = 0;
            mImgHei = 0;
        }
        setPadding();
    }

    public void setMyPadding(int left, int top, int right, int bottom) {
        oldPaddingTop = top;
        oldPaddingLeft = left;
        oldPaddingRight = right;
        oldPaddingBottom = bottom;
        isSetPadding = true;
        setPadding(left + getBitmapWidth() / 2, top + getBitmapHeigh(), right
                + getBitmapWidth() / 2, bottom);
        isSetPadding = false;
    }

    public void setTextSize(int textsize) {
        this.textSize = textsize;
        mPaint.setTextSize(textsize);
    }

    public void setTextColor(int color) {
        mPaint.setColor(color);
    }

    public void setTextPadding(int top, int left) {
        this.textPaddingLeft = left;
        this.textPaddingTop = top;
    }

    public void setImagePadding(int top, int left) {
        this.imagePaddingLeft = left;
        this.imagePaddingTop = top;
    }

    private int getBitmapWidth() {
        return (int) Math.ceil(mImgWidth);
    }

    private int getBitmapHeigh() {
        return (int) Math.ceil(mImgHei);
    }

    private float getTextHei() {
        FontMetrics fm = mPaint.getFontMetrics();
        return (float) Math.ceil(fm.descent - fm.top) + 2;
    }

    public int getTextPaddingLeft() {
        return textPaddingLeft;
    }

    public int getTextPaddingTop() {
        return textPaddingTop;
    }

    public int getImagePaddingLeft() {
        return imagePaddingLeft;
    }

    public int getImagePaddingTop() {
        return imagePaddingTop;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setHide(boolean hide) {
        this.isHide = hide;
    }

    public boolean isHide() {
        return isHide;
    }

}

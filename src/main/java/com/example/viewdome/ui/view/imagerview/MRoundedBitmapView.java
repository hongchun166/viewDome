package com.example.viewdome.ui.view.imagerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.viewdome.R;


/**
 * Created by Administrator on 2015/8/29.
 */
public class MRoundedBitmapView extends ImageView {


    /**
     * TYPE_CIRCLE / TYPE_ROUND
     */
    private int type;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;

    /**
     * 圆角的大小
     */
    private int mRadius;

    /**
     * 图片
     */
    private Bitmap mSrc;

    // 控件默认长、宽
    private int defaultWidth = 0;
    private int defaultHeight = 0;
    // 边框粗细
    float mBorderThickness=0;
    private int defaultColor = 0xFFFFFFFF;
    // 如果只有其中一个有值，则只画一个圆形边框
    private int mBorderOutsideColor = 0;
    private int mBorderInsideColor = 0;


    public MRoundedBitmapView(Context context) {
        this(context, null);
    }

    public MRoundedBitmapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MRoundedBitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MRoundedBitmapView, defStyle, 0);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MRoundedBitmapView_type:
                    // 获取是圆角图还是圆形图
                    type = a.getInt(attr, 0);// 默认为Circle
                    break;
                case R.styleable.MRoundedBitmapView_borderRadius:
                    // 圆角图的圆角大小 默认10dp
                    mRadius = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                            getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MRoundedBitmapView_border_thickness:
                    // 边框大小 厚度
                    mBorderThickness = a.getDimensionPixelSize(
                            R.styleable.MRoundedBitmapView_border_thickness, 0);
                    break;
                case R.styleable.MRoundedBitmapView_border_inside_color:
                    // 内边框颜色
                    mBorderOutsideColor = a
                            .getColor(R.styleable.MRoundedBitmapView_border_outside_color,
                                    defaultColor);
                    break;
                case R.styleable.MRoundedBitmapView_border_outside_color:
                    // 外边框颜色
                    mBorderInsideColor = a.getColor(
                            R.styleable.MRoundedBitmapView_border_inside_color, defaultColor);
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        this.measure(0, 0);
        if (drawable.getClass() == NinePatchDrawable.class)
            return;
//        Bitmap b= ((BitmapDrawable) drawable).getBitmap();
//        mSrc= b.copy(Bitmap.Config.ARGB_8888, true);
        mSrc= ((BitmapDrawable) drawable).getBitmap();

        if (defaultWidth == 0) {
            defaultWidth = getWidth();

        }
        if (defaultHeight == 0) {
            defaultHeight = getHeight();
        }
        int min=0;

        // 判断是圆形还是圆角
        switch (type) {
            case TYPE_CIRCLE:

                 min = Math.min(defaultWidth, defaultHeight);
                /**
                 * 长度如果不一致，按小的值进行压缩
                 */
                mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
                canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);
                break;
            case TYPE_ROUND:
                min = Math.min(defaultWidth, defaultHeight);
                /**
                 * 长度如果不一致，按小的值进行压缩
                 */
                mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
                canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0, null);
                break;

        }
    }
    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param source
     * @param min
     * @return
     */
    private Bitmap createCircleImage(Bitmap source, int min)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);

        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);

        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);

        /**
         * 使用SRC_IN，参考上面的说明
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }

    /**
     * 根据原图添加圆角
     *
     * @param source
     * @return
     */
    private Bitmap createRoundConerImage(Bitmap source)
    {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);

        Bitmap target = Bitmap.createBitmap(defaultWidth, defaultWidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());

        canvas.drawRoundRect(rect,mRadius, mRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        return target;
    }
    /**
     * 边缘画圆
     */
    private void drawCircleBorder(Canvas canvas, int radius, int color) {
        Paint paint = new Paint();
		/* 去锯齿 */
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(color);
		/* 设置paint的　style　为STROKE：空心 */
        paint.setStyle(Paint.Style.STROKE);
		/* 设置paint的外框宽度 */
        paint.setStrokeWidth(mBorderThickness);
        canvas.drawCircle(defaultWidth / 2, defaultHeight / 2, radius, paint);
    }
}

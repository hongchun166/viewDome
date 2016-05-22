package com.example.viewdome.ui.view.progress;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import com.ws.efamilylove.R;

/**
 * Created by TianHongChun on 2016/5/21.
 */
public class CircleProgressView extends View {

    private Context context;
    int roundColor;
    int roundProgressColorStart;
    int roundProgressColorEnd;
    int  roundWidth;

    Paint paint;
    Paint textPaint;
    /**
     * 当前进度
     */
    private int progress;
    int max;
    int startAngle;
    int textSize;
    int textColor;
    String textBottom;
    String textTop;


    public CircleProgressView(Context context) {
        this(context, null);
    }
    public CircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs,defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr){
        this.context=context;
        TypedArray mTypedArray=context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);

        max=360;
        startAngle=-90;
        textSize=42;
        textColor=Color.WHITE;
        textBottom=getResources().getString(R.string.main_head_action);
        textTop="0";
        roundColor=mTypedArray.getColor(R.styleable.CircleProgressView_roundColor, Color.parseColor("#e8e8e8"));
        roundProgressColorStart=mTypedArray.getColor(R.styleable.CircleProgressView_roundProgressColorStart, Color.parseColor("#aaffd4"));
        roundProgressColorEnd=mTypedArray.getColor(R.styleable.CircleProgressView_roundProgressColorEnd, Color.parseColor("#00bf5f"));
        roundWidth=(int) mTypedArray.getDimension(R.styleable.CircleProgressView_roundWidth,20);
        mTypedArray.recycle();

        textPaint=new Paint();
        paint = new Paint();
        initPaint();
    }

    private void initPaint(){
        textPaint.setStrokeWidth(0);
        textPaint.setColor(textColor);
        textPaint.setTypeface(Typeface.MONOSPACE); //设置字体
        textPaint.setAntiAlias(true);  //消除锯齿

        paint.setStyle(Paint.Style.STROKE); //设置空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setAntiAlias(true);  //消除锯齿

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        /**
         * 画最外层的大圆环
         */
        int centre = getWidth()/2; //获取圆心的x坐标
        int radius = (int) (centre - roundWidth/2); //圆环的半径

        paint.setColor(roundColor); //设置圆环的颜色
        canvas.drawCircle(centre, centre, radius, paint); //画出圆环

        //画字
        textPaint.setTextSize(textSize);
        float textWidth = textPaint.measureText(textBottom);
        canvas.drawText(textBottom,centre - textWidth / 2, centre +textSize/2+20,textPaint);

        textPaint.setTextSize(textSize+6);
        textWidth=textPaint.measureText(textTop);
        canvas.drawText(textTop,centre - textWidth / 2, centre - textSize/2,textPaint);

        //设置进度是实心还是空心
        paint.setStrokeCap(Paint.Cap.ROUND);//笔尖圆形,
        paint.setColor(roundProgressColorEnd);  //设置进度的颜色
        paint.setStrokeMiter(roundWidth/2);
        RectF oval = new RectF(centre - radius, centre - radius, centre+ radius, centre + radius);  //用于定义的圆弧的形状和大小的界限
        canvas.drawArc(oval,startAngle, -360 * progress / max, false, paint);  //根据进度画圆弧

        canvas.restore();
        paint.reset();

    }

    /**
     * 设置进度，此为线程安全控件，由于考虑多线的问题，需要同步
     * 刷新界面调用postInvalidate()能在非UI线程刷新
     * @param progress
     */
    public synchronized void setProgress(int progress,String values) {
        if(progress < 0){
            throw new IllegalArgumentException("progress not less than 0");
        }
        this.progress = progress > max ? max : progress;
        this.textTop=values;
        postInvalidate();
    }

    public int getRoundProgressColorEnd() {
        return roundProgressColorEnd;
    }

    public void setRoundProgressColorEnd(int roundProgressColorEnd) {
        this.roundProgressColorEnd = roundProgressColorEnd;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }
}

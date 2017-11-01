package com.zmin.quickindexbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

/**
 * @author: ZhangMin
 * @date:  2017/11/1 16:20
 * @desc: 侧面的快速索引
 */
public class QuickIndexBar extends View {

    /** 字母更新的监听器 */
    private OnLetterUpdateListener onLetterUpdateListener;
    /** 当前的字母指示的位置 */
    private int currentIndex = -100;
    /** 是否松手 */
    private boolean isUp = true;
    /** 字母测量的尺寸 */
    private float measureText;
    /** 数字模拟器 */
    private Scroller scroller;
    /** 触摸临界值 */
    private float touchSlop;
    /** 起始位置 */
    private int startX = 100;
    /** 变化比率 */
    private float mX = 100;

    //up事件的接口
    public interface OnUpListener {
        void up();
    }

    private OnUpListener onUpListener;


    public interface OnLetterUpdateListener {
        void onLetterUpdate(String letter);
    }

    public OnUpListener getOnUpListener() {
        return onUpListener;
    }

    public void setOnUpListener(OnUpListener onUpListener) {
        this.onUpListener = onUpListener;
    }

    public void setOnLetterUpdateListener(OnLetterUpdateListener onLetterUpdateListener) {
        this.onLetterUpdateListener = onLetterUpdateListener;
    }

    private static final String[] LETTERS = new String[]{"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Paint paint;
    // 单元格宽度
    private int cellWidth;
    // 单元格高度
    private float cellHeight;

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * 初始化数字模拟器
     */
    private void init() {
        scroller = new Scroller(getContext());
        touchSlop = ViewConfiguration.get(getContext()).getTouchSlop();

        // 创建一个抗锯齿的画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 画笔文本加粗
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        // 颜色
        paint.setColor(getResources().getColor(R.color.text_blue_search));
        setTextSize(26);

        measureText = paint.measureText("M");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mHeight = getMeasuredHeight();
        cellWidth = getMeasuredWidth();
        cellHeight = mHeight * 1.0f / LETTERS.length;
        if (!isUp) {

            setMeasuredDimension(widthMeasureSpec * 3, heightMeasureSpec);
        } else {
            setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // 遍历26个英文字母, 计算坐标, 进行绘制
        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];

            // 计算x坐标
            float dx = 0;
            //float x = cellWidth * 0.5f - paint.measureText(letter) * 0.5f + 80;
            //  float x = cellWidth - paint.measureText(letter);
            float x = cellWidth - measureText * 1.5f;
            if (!isUp) {
                mX = 1.0f;
            }
            switch (currentIndex - i) {
                case -4:
                    dx = measureText * 1.5f * mX;
                    paint.setColor(getResources().getColor(R.color.text_blue_search_4));
                    setTextSize(27);
                    break;
                case -3:
                    dx = measureText * 2.5f * mX;
                    paint.setColor(getResources().getColor(R.color.text_blue_search_3));
                    setTextSize(33);
                    break;
                case -2:
                    dx = measureText * 4.0f * mX;
                    paint.setColor(getResources().getColor(R.color.text_blue_search_2));
                    setTextSize(38);
                    break;
                case -1:
                    dx = measureText * 5.0f * mX;
                    paint.setColor(getResources().getColor(R.color.text_blue_search_1));
                    setTextSize(42);
                    break;
                case 0:
                    paint.setColor(getResources().getColor(R.color.text_blue_search));
                    dx = measureText * 6.0f * mX;
                    setTextSize(50);
                    break;
                case 1:
                    dx = measureText * 5.0f * mX;
                    paint.setColor(getResources().getColor(R.color.text_blue_search_1));
                    setTextSize(42);
                    break;
                case 2:
                    dx = measureText * 4.0f * mX;
                    paint.setColor(getResources().getColor(R.color.text_blue_search_2));
                    setTextSize(38);
                    break;
                case 3:
                    dx = measureText * 2.5f * mX;
                    paint.setColor(getResources().getColor(R.color.text_blue_search_2));
                    setTextSize(33);
                    break;
                case 4:
                    dx = measureText * 1.5f * mX;
                    paint.setColor(getResources().getColor(R.color.text_blue_search_3));
                    setTextSize(28);
                    break;
                default:
                    dx = 0;
                    paint.setColor(getResources().getColor(R.color.text_blue_search_4));
                    setTextSize(26);
                    break;
            }
            if (!isUp) { //触摸状态
                x -= dx;
            } else {  //松手状态
                //  x = cellWidth * 0.8f - paint.measureText(letter) * 0.5f ;
                //x =  cellWidth - measureText*1.5f;
                x -= dx;
                paint.setColor(getResources().getColor(R.color.text_blue_search));
                setTextSize(26);
            }

            // 计算y坐标
            Rect bounds = new Rect();
            // 获取文本的矩形区域
            paint.getTextBounds(letter, 0, letter.length(), bounds);
            float y = cellHeight * 0.5f + bounds.height() * 0.5f + i * cellHeight;

            // 绘制文本
            if (letter.equals("I")) {
                x += measureText * 0.2f;
            }
            canvas.drawText(letter, x, y, paint);
        }
    }

    private int lastIndex = -1;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float y;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scroller.forceFinished(true);
                isUp = false;
                setPadding(500, 0, 0, 0);
                // 获取被点击到的字母索引
                y = event.getY();
                // 根据y值, 计算当前按下的字母位置
                currentIndex = (int) (y / cellHeight);
                if (currentIndex != lastIndex) {
                    if (currentIndex >= 0 && currentIndex < LETTERS.length) {
                        String letter = LETTERS[currentIndex];
                        if (onLetterUpdateListener != null) {
                            onLetterUpdateListener.onLetterUpdate(letter);
                        }
                        // 记录上一次触摸的字母
                        lastIndex = currentIndex;
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                // 获取被点击到的字母索引
                y = event.getY();
                // 根据y值, 计算当前按下的字母位置
                currentIndex = (int) (y / cellHeight);
                if (currentIndex != lastIndex) {
                    if (currentIndex >= 0 && currentIndex < LETTERS.length) {
                        String letter = LETTERS[currentIndex];
                        if (onLetterUpdateListener != null) {
                            onLetterUpdateListener.onLetterUpdate(letter);
                        }
                        // 记录上一次触摸的字母
                        lastIndex = currentIndex;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                isUp = true;
                lastIndex = -1;
                //调用回调里面的方法
                if (null != onUpListener) {
                    onUpListener.up();
                }
                smaooth();

                break;
            default:
                break;
        }
        invalidate();
        return true;
    }


    /**
     * 开启数字模拟器
     */
    private void smaooth() {
        scroller.startScroll(startX, 0, -99, 0, 550);
        //invalidate();
    }

    @Override
    public void computeScroll() {
        // 当scroller数据模拟完毕时, 不应该继续进行递归
        // 反之, 如果正在模拟数据才进行递归的操作

        if (scroller.computeScrollOffset()) {// 当前还是正在模拟数据中
            // 把当前scroller正在模拟的数据取出来, 使用scrollTo方法切换屏幕
            mX = ((float) scroller.getCurrX()) / 100f;
            // scrollTo(currX, 0);
            if (mX == 0.01f) {
                setPadding(0, 0, 0, 0);
            }
            invalidate(); // 在触发当前方法, 相当于递归.
        }
    }


    private void setTextSize(int size) {
        paint.setTextSize(size+10);
    }

}

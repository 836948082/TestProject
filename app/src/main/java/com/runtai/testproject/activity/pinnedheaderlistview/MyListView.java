package com.runtai.testproject.activity.pinnedheaderlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * @作者：高炎鹏
 * @日期：2016/11/3时间15:53
 * @描述：
 */
public class MyListView extends ListView {
    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public MyListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        int width = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            if (widthMode == MeasureSpec.AT_MOST) {
                final int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View view = getChildAt(i);
                    measureChild(view, widthMeasureSpec, heightMeasureSpec);
                    width = Math.max(width, view.getMeasuredWidth());
                }
            }
        }
        setMeasuredDimension(width, height);
    }
}
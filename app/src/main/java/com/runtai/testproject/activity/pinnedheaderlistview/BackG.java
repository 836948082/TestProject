package com.runtai.testproject.activity.pinnedheaderlistview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.runtai.testproject.R;

/**
 * @作者：高炎鹏
 * @日期：2016/11/2时间17:31
 * @描述：
 */
public class BackG extends View{

    public BackG(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLUE);// 设置白色
        paint.setAntiAlias(true);// 抗锯齿效果
        canvas.drawRect(0, 0, 300, 300, paint);
        //绘制图片
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.icon_2), 150, 150, paint);
    }
}

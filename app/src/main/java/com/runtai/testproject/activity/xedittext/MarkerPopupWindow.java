package com.runtai.testproject.activity.xedittext;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.runtai.testproject.R;

public class MarkerPopupWindow extends PopupWindow {

    public MarkerPopupWindow(Context context, View rootLayout, int x, int y) {
        super(context);

        View view = View.inflate(context, R.layout.layout_popup, null);

        this.setContentView(view);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
        this.setFocusable(true);
        this.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        this.setOutsideTouchable(true);
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setAnimationStyle(R.style.PopupWindow_Animation);
        this.showAtLocation(rootLayout, Gravity.NO_GRAVITY, x, y - rootLayout.getHeight() - 100); // adjust a little bit
    }

}

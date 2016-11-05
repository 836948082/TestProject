package com.runtai.testproject.activity.labeleffect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.runtai.labeleffect.LabelButtonView;
import com.runtai.labeleffect.LabelImageView;
import com.runtai.labeleffect.LabelTextView;
import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

/**
 * @作者：高炎鹏
 * @日期：2016/11/3时间17:53
 * @描述：贴纸效果 LabelEffectActivity
 */
public class LabelEffectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_effect);

        initView();
    }

    private void initView() {
        final LabelButtonView labelButtonView = (LabelButtonView) findViewById(R.id.labelbutton);
        labelButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labelButtonView.setLabelVisual(!labelButtonView.isLabelVisual());
            }
        });

        final LabelImageView labelImageView1 = (LabelImageView) findViewById(R.id.image1);
        labelImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labelImageView1.setLabelDistance(50);
            }
        });

        final LabelImageView labelImageView2 = (LabelImageView) findViewById(R.id.image2);
        labelImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labelImageView2.setLabelText("ART");
            }
        });


        final LabelTextView labelTextView = (LabelTextView) findViewById(R.id.text);
        labelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labelTextView.setLabelOrientation(3);
            }
        });


        findViewById(R.id.click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabelEffectActivity.this, ListViewActivity.class));
            }
        });

        findViewById(R.id.click11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabelEffectActivity.this, RecyclerViewActivity.class));
            }
        });
    }
}

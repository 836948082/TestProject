package com.runtai.testproject.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.runtai.niftydialogeffectslibrary.Effectstype;
import com.runtai.niftydialogeffectslibrary.NiftyDialogBuilder;
import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

/**
 * @作者：高炎鹏
 * @时间：2016/10/28 10:57
 * @描述：多种Dialog动画效果界面
 */
public class NiftyDialogEffectsActivity extends BaseActivity implements View.OnClickListener {

    Effectstype effect;
    Button fadein, slideright, slideleft, slidetop, slideBottom, newspager, fall, sidefall, shake, fliph, flipv, rotatebottom, rotateleft, slit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niftydialogeffects);
        initView();
    }

    public void initView() {
        fadein = (Button) findViewById(R.id.fadein);
        fadein = (Button) findViewById(R.id.fadein);
        slideright = (Button) findViewById(R.id.slideright);
        slideleft = (Button) findViewById(R.id.slideleft);
        slidetop = (Button) findViewById(R.id.slidetop);
        slideBottom = (Button) findViewById(R.id.slideBottom);
        newspager = (Button) findViewById(R.id.newspager);
        fall = (Button) findViewById(R.id.fall);
        sidefall = (Button) findViewById(R.id.sidefall);
        shake = (Button) findViewById(R.id.shake);
        fliph = (Button) findViewById(R.id.fliph);
        flipv = (Button) findViewById(R.id.flipv);
        rotatebottom = (Button) findViewById(R.id.rotatebottom);
        rotateleft = (Button) findViewById(R.id.rotateleft);
        slit = (Button) findViewById(R.id.slit);

        fadein.setOnClickListener(this);
        slideright.setOnClickListener(this);
        slideleft.setOnClickListener(this);
        slidetop.setOnClickListener(this);
        slideBottom.setOnClickListener(this);
        newspager.setOnClickListener(this);
        fall.setOnClickListener(this);
        sidefall.setOnClickListener(this);
        shake.setOnClickListener(this);
        fliph.setOnClickListener(this);
        flipv.setOnClickListener(this);
        rotatebottom.setOnClickListener(this);
        rotateleft.setOnClickListener(this);
        slit.setOnClickListener(this);
    }

    public void dialogShow(View view) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        dialogBuilder
                .withTitle("Modal Dialog")                                  //.withTitle(null)  no title
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessage("This is tip_3 modal Dialog.")                     //.withMessage(null)  no Msg
                .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)                               //def
                .withIcon(getResources().getDrawable(R.drawable.github_icon))
                .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                .withDuration(700)                                          //def
                .withEffect(effect)                                         //def Effectstype.Slidetop
                .withButton1Text("OK")                                      //def gone
                .withButton2Text("Cancel")                                  //def gone
                .setCustomView(R.layout.custom_view, view.getContext())         //.setCustomView(View or ResId,context)
                .setPositiveButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fadein:
                effect = Effectstype.Fadein;
                break;
            case R.id.slideright:
                effect = Effectstype.Slideright;
                break;
            case R.id.slideleft:
                effect = Effectstype.Slideleft;
                break;
            case R.id.slidetop:
                effect = Effectstype.Slidetop;
                break;
            case R.id.slideBottom:
                effect = Effectstype.SlideBottom;
                break;
            case R.id.newspager:
                effect = Effectstype.Newspager;
                break;
            case R.id.fall:
                effect = Effectstype.Fall;
                break;
            case R.id.sidefall:
                effect = Effectstype.Sidefill;
                break;
            case R.id.fliph:
                effect = Effectstype.Fliph;
                break;
            case R.id.flipv:
                effect = Effectstype.Flipv;
                break;
            case R.id.rotatebottom:
                effect = Effectstype.RotateBottom;
                break;
            case R.id.rotateleft:
                effect = Effectstype.RotateLeft;
                break;
            case R.id.slit:
                effect = Effectstype.Slit;
                break;
            case R.id.shake:
                effect = Effectstype.Shake;
                break;
            default:
                break;
        }
        dialogShow(view);
    }
}

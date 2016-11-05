package com.runtai.testproject.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

import java.util.Calendar;

/**
 * @作者：高炎鹏
 * @时间：2016/10/27 17:14
 * @描述：日期、时间选择器
 */
public class DatePickerActivity extends BaseActivity implements View.OnClickListener {

    private TextView dp_show, tp_show;
    private Button dp_choose, tp_choose;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private static final int DATE_DIALOG = 1;
    private static final int TIME_DIALOG = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);
        dp_show = (TextView) findViewById(R.id.dp_show);
        dp_choose = (Button) findViewById(R.id.dp_choose);
        dp_choose.setOnClickListener(this);
        getDate();
        tp_show = (TextView) findViewById(R.id.tp_show);
        tp_choose = (Button) findViewById(R.id.tp_choose);
        tp_choose.setOnClickListener(this);
        getTime();
    }

    public void getDate() {
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
    }

    public void getTime() {
        final Calendar ca = Calendar.getInstance();
        mHour = ca.get(Calendar.HOUR);
        mMinute = ca.get(Calendar.MINUTE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dp_choose:
                showDialog(DATE_DIALOG);
                break;
            case R.id.tp_choose:
                showDialog(TIME_DIALOG);
                break;
            default:
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, DPListener, mYear, mMonth, mDay);
            case TIME_DIALOG:
                return new TimePickerDialog(this, TPListener, mMonth, mDay, true);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener DPListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            showDate();
        }
    };

    private TimePickerDialog.OnTimeSetListener TPListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            showTime();
        }
    };

    /**
     * 设置日期 利用StringBuffer追加
     *
     * 小于10自动补0
     */
    public void showDate() {
        dp_show.setText(new StringBuffer().append(mYear).append("-").append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1)).append("-").append(mDay < 10 ? "0" + mDay : mDay));
    }

    /**
     * 设置时间 利用StringBuffer追加
     *
     * 小于10自动补0
     */
    public void showTime() {
        tp_show.setText(new StringBuffer().append(mHour < 10 ? "0" + mHour : mHour).append(":").append(mMinute < 10 ? "0" + mMinute : mMinute));
    }

}
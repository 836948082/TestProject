package com.runtai.countdown;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class RoundProgressBar extends View {

	private static final String TAG = RoundProgressBar.class.getSimpleName();

	/**
	 * 画笔对象的引用
	 */
	private Paint paint;

	/**
	 * 圆环的颜色
	 */
	private int roundColor;

	/**
	 * 圆环进度的颜色
	 */
	private int roundProgressColor;

	/**
	 * 中间的字符串的颜色
	 */
	private int textColor;

	/**
	 * 中间的字符串的字体
	 */
	private float textSize;

	/**
	 * 圆环的宽度
	 */
	private float roundWidth;

	private float progress;

	private CountDownTimerListener listener;

	private Context context;

	public RoundProgressBar(Context context) {
		this(context, null);
	}

	public RoundProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		this.context = context;
		paint = new Paint();

		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBar);

		// 获取自定义属性和默认值
		roundColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_roundColor, 0x50555555);
		roundProgressColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_roundProgressColor, 0xFF6ADBFE);
		textColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_textColor, 0xFFFFFFFF);
		textSize = mTypedArray.getDimension(
				R.styleable.RoundProgressBar_textSize, 15);
		roundWidth = mTypedArray.getDimension(
				R.styleable.RoundProgressBar_roundWidth, 5);

		mTypedArray.recycle();

		this.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				progress = 360;
				invalidate();
				countDownTimer.cancel();
				if (listener != null) {
					listener.onStop();
				}
			}
		});
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		/**
		 * 画最外层的大圆环
		 */
		int centre = getWidth() / 2; // 获取圆心的x坐标
		int radius = (int) (centre - roundWidth / 2); // 圆环的半径
		paint.setColor(roundColor); // 设置圆环的颜色
		paint.setStyle(Paint.Style.FILL); // 设置实心
		paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
		paint.setAntiAlias(true); // 消除锯齿
		canvas.drawCircle(centre, centre, radius, paint); // 画出圆环

		/**
		 * 画进度百分比
		 */
		paint.setStrokeWidth(0);
		paint.setColor(textColor);
		paint.setTextSize(textSize);
		paint.setTypeface(Typeface.DEFAULT_BOLD); // 设置字体(加粗)

		float textWidth = paint.measureText("跳过"); // 测量字体宽度，我们需要根据字体的宽度设置在圆环中间
		canvas.drawText("跳过", centre - textWidth / 2, centre + textSize / 2,
				paint); // 画出进度百分比

		/**
		 * 画圆弧 ，画圆环的进度
		 */

		// 设置进度是实心还是空心
		paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
		paint.setColor(roundProgressColor); // 设置进度的颜色
		RectF oval = new RectF(centre - radius, centre - radius, centre
				+ radius, centre + radius); // 用于定义的圆弧的形状和大小的界限

		paint.setStyle(Paint.Style.STROKE);
		canvas.drawArc(oval, 0, progress, false, paint); // 根据进度画圆弧

	}

	public int getCricleColor() {
		return roundColor;
	}

	public void setCricleColor(int cricleColor) {
		this.roundColor = cricleColor;
	}

	public int getCricleProgressColor() {
		return roundProgressColor;
	}

	public void setCricleProgressColor(int cricleProgressColor) {
		this.roundProgressColor = cricleProgressColor;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public float getTextSize() {
		return textSize;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}

	public float getRoundWidth() {
		return roundWidth;
	}

	public void setRoundWidth(float roundWidth) {
		this.roundWidth = roundWidth;
	}

	public void setCountDownTimerListener(CountDownTimerListener listener) {
		this.listener = listener;
	}

	CountDownTimer countDownTimer;

	public void start() {
		start(3000, 50);
	}

	public void start(final int mill, int count) {
		if (listener != null) {
			listener.onStartCount();
		}
		countDownTimer = new CountDownTimer(mill, count) {
			@Override
			public void onTick(long millisUntilFinished) {
				// 必须做非空判断
				if (((Activity) context).isFinishing()) {
					return;
				}

				Log.e("millisUntilFinished", "" + millisUntilFinished);
				progress = (mill - millisUntilFinished) * 360 / mill;
				Log.e(TAG, "progress:" + progress);
				invalidate();
			}

			@Override
			public void onFinish() {
				progress = 360;
				invalidate();
				if (listener != null) {
					listener.onFinishCount();
				}
			}
		}.start();
	}

	public interface CountDownTimerListener {

		void onStartCount();

		void onFinishCount();

		void onStop();
	}

}

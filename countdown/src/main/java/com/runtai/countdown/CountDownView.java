package com.runtai.countdown;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CountDownView extends View {

	private static final String TAG = CountDownView.class.getSimpleName();
	private static final int BACKGROUND_COLOR = 0x50555555;
	private static final float BORDER_WIDTH = 15f;
	private static final int BORDER_COLOR = 0xFF6ADBFE;
	private static final String TEXT = "跳过广告";
	private static final float TEXT_SIZE = 50f;
	private static final int TEXT_COLOR = 0xFFFFFFFF;

	private int backgroundColor;// 背景颜色
	private float borderWidth;// 进度条粗细
	private int borderColor;// 进度条的颜色
	private String text;// 文字内容
	private int textColor;// 文字颜色
	private float textSize;// 文字大小

	private Paint circlePaint;
	private TextPaint textPaint;
	private Paint borderPaint;

	private float progress = 0;
	private StaticLayout staticLayout;// 实现了文本绘制换行处理

	private CountDownTimerListener listener;
	private Context context;

	public CountDownView(Context context) {
		this(context, null);
	}

	public CountDownView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		TypedArray ta = context.obtainStyledAttributes(attrs,
				R.styleable.CountDownView);
		backgroundColor = ta.getColor(
				R.styleable.CountDownView_background_color, BACKGROUND_COLOR);
		borderWidth = ta.getDimension(R.styleable.CountDownView_border_width,
				BORDER_WIDTH);
		borderColor = ta.getColor(R.styleable.CountDownView_border_color,
				BORDER_COLOR);
		text = ta.getString(R.styleable.CountDownView_text);

		if (text == null) {
			text = TEXT;
		}
		textSize = ta.getDimension(R.styleable.CountDownView_text_size,
				TEXT_SIZE);
		textColor = ta.getColor(R.styleable.CountDownView_text_color,
				TEXT_COLOR);
		ta.recycle();
		init();
	}

	private void init() {
		circlePaint = new Paint();
		circlePaint.setAntiAlias(true);// 消除锯齿
		circlePaint.setDither(true);// 设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
		circlePaint.setColor(backgroundColor);
		circlePaint.setStyle(Paint.Style.FILL);

		textPaint = new TextPaint();
		textPaint.setAntiAlias(true);
		textPaint.setDither(true);
		textPaint.setColor(textColor);
		textPaint.setTextSize(textSize);
		textPaint.setTextAlign(Paint.Align.CENTER);

		borderPaint = new Paint();
		borderPaint.setAntiAlias(true);
		borderPaint.setDither(true);
		borderPaint.setColor(borderColor);
		borderPaint.setStrokeWidth(borderWidth);
		borderPaint.setStyle(Paint.Style.STROKE);

		int textWidth = (int) textPaint.measureText(text.substring(0,
				(text.length() + 1) / 2));
		staticLayout = new StaticLayout(text, textPaint, textWidth,
				Layout.Alignment.ALIGN_NORMAL, 1F, 0, false);
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

	// 重写此方法的目的是测量控件的实际大小，当width和height是wrap_content，我们需要测量控件的实际大小
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (widthMode != MeasureSpec.EXACTLY) {
			width = staticLayout.getWidth();
		}
		if (heightMode != MeasureSpec.EXACTLY) {
			height = staticLayout.getHeight();
		}
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int width = getMeasuredWidth();
		int height = getMeasuredHeight();
		int min = Math.min(width, height);
		// 画底盘
		canvas.drawCircle(width / 2, height / 2, min / 2, circlePaint);
		// 画边框
		RectF rectF;
		if (width > height) {
			rectF = new RectF(width / 2 - min / 2 + borderWidth / 2,
					0 + borderWidth / 2, width / 2 + min / 2 - borderWidth / 2,
					height - borderWidth / 2);
		} else {
			rectF = new RectF(borderWidth / 2, height / 2 - min / 2
					+ borderWidth / 2, width - borderWidth / 2, height / 2
					- borderWidth / 2 + min / 2);
		}
		canvas.drawArc(rectF, 0, progress, false, borderPaint);
		// 画居中的文字
		// canvas.drawText("稍等片刻", width / 2, height / 2 - textPaint.descent() +
		// textPaint.getTextSize() / 2, textPaint);
		canvas.translate(width / 2, height / 2 - staticLayout.getHeight() / 2);
		staticLayout.draw(canvas);
	}

	CountDownTimer countDownTimer;

	public void start() {
		start(6000, 200);
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

	public void setCountDownTimerListener(CountDownTimerListener listener) {
		this.listener = listener;
	}

	public interface CountDownTimerListener {

		void onStartCount();

		void onFinishCount();

		void onStop();
	}
}
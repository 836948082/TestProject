# TestProject

测试专用工程

1.集成对于Android6.0动态权限
2.集成logcat日志打印可追踪Module

3.继承BaseActivity 可右滑关闭当前Activity
4.添加RunningTextView 类--实现播放数字动画类
5.添加AutoScrollTextView 类--实现水平滚动跑马灯效果控件

6.添加登录界面ViewPager可左右滑动，并设置中间线和导航线、宽度和颜色可设置
7.实现右进右出的进场、退场动画
8.实现右滑关闭当前Activity

9.使用功能强大的SuperTextView控件实现支付方式，setOnSuperTextViewClickListener后可设置多种监听
    1、上下线可以通过 sLineShow 设置(默认bottom) --->> 有四种显示方式 none、top、bottom、both(无、上、下、上下)
    2、可通过设置 sUseRipple=true 开启水波效果

    superTextView.setCbChecked(true)
        .setLeftString("")
        .setLeftIcon(drawable)
        .setLeftTVColor(0)
        .setLeftTopString("")
        .setLeftTopTVColor(0)
        .setLeftBottomString("")
        .setLeftBottomTVColor(0)
        .setLeftBottomString2("")
        .setLeftBottomTVColor2(0)
        .setRightString("")
        .setRightTVColor(0)
        .setCbBackground(drawable)
        .setRightIcon(drawable)
        .setRightString("")
        .setRightTVColor(0)
        .setLeftString("");

    stv:sLineShow = "both"//设置控件上下线<有四种显示方式 none、top、bottom、both(无、上、下、上下)>
    stv:sRightCheckBoxShow="true"//设置控件水波效果
    stv:sRightCheckBoxShow="true"//设置控件右边显示CheckBox控件
    stv:sRightCheckBoxRes="@drawable/circular_check_bg"//设置CheckBox控件check状态

    stv:sBottomLineMargin="0dp"//设置控件下面的线的Margin
    stv:sCenterTextString="中间"//设置控件中间文字
    stv:sLeftTextString="左边"//设置控件左边文字
    stv:sRightTextString="右边"//设置控件右边文字

    superTextView.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
          @Override
          public void onSuperTextViewClick() {
              super.onSuperTextViewClick();
              //控件点击事件
          }

          @Override
          public void onLeftTopClick() {
              super.onLeftTopClick();
              //控件中(LeftTop(左上TextView))点击事件
          }

          @Override
          public void onLeftBottomClick() {
              super.onLeftBottomClick();
              //控件中(LeftBottom(左下TextView))点击事件
          }

          @Override
          public void onLeftBottomClick2() {
              super.onLeftBottomClick2();
              //控件中(LeftBottom(左下第二个TextView))点击事件
          }
      });


      activity_bowen.xml中按钮的点击效果
      1、列表
      android:background="?android:selectableItemBackground"
      2、按钮
      android:background="?android:attr/selectableItemBackground" (有界波纹)
      android:background="?android:attr/selectableItemBackgroundBorderless" (无界波纹)

      android:colorControlHighlight：设置波纹颜色
      android:colorAccent：设置checkbox等控件的选中颜色
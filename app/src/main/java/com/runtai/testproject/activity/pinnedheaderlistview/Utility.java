package com.runtai.testproject.activity.pinnedheaderlistview;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Utility {

	/** 计算ListView的实际高度 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		Log.e("params.height",""+params.height);
		listView.setLayoutParams(params);
	}

	/**
	 * 计算GridView的实际高度
	 * num 指Gridview多少列
	 */
	public static void setGridViewHeightBasedOnChildren(GridView gridview, int num) {
		ListAdapter listAdapter = gridview.getAdapter();
		if (listAdapter == null) {
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, gridview);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = gridview.getLayoutParams();
		params.height = totalHeight/num + (gridview.getVerticalSpacing() * (listAdapter.getCount() - 1));
		gridview.setLayoutParams(params);
	}
}
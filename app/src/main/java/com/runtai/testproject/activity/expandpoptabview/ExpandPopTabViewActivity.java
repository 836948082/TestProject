package com.runtai.testproject.activity.expandpoptabview;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.expandpoptabview.ExpandPopTabView;
import com.expandpoptabview.KeyValueBean;
import com.expandpoptabview.PopOneListView;
import com.expandpoptabview.PopTwoListView;
import com.runtai.testproject.R;
import com.runtai.testproject.activity.expandpoptabview.config.ConfigAreaDTO;
import com.runtai.testproject.activity.expandpoptabview.config.ConfigsDTO;
import com.runtai.testproject.activity.expandpoptabview.config.ConfigsMessageDTO;
import com.runtai.testproject.cehua.BaseActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者：高炎鹏
 * @日期：2016/11/4时间13:43
 * @描述：分类筛选
 */
public class ExpandPopTabViewActivity extends BaseActivity {

    private ExpandPopTabView expandTabView;
    private List<KeyValueBean> mParentLists = new ArrayList<>();
    private List<ArrayList<KeyValueBean>> mChildrenListLists = new ArrayList<>();
    private List<KeyValueBean> mPriceLists;
    private List<KeyValueBean> mSortLists;
    private List<KeyValueBean> mFavorLists;

    private TextView eptv_show_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandpoptabview);

        eptv_show_tv = (TextView) findViewById(R.id.eptv_show_tv);
        eptv_show_tv.setText("筛选后显示");

        setConfigsDatas();

        expandTabView = (ExpandPopTabView) findViewById(R.id.expandtab_view);
        addItem(expandTabView, mPriceLists, "", "价格");
        addItem(expandTabView, mFavorLists, "默认", "排序");
        addItem(expandTabView, mSortLists, "优惠最多", "优惠");
        addItem(expandTabView, mParentLists, mChildrenListLists, "锦江区", "合江亭", "区域");
    }

    /**
     * 水平增加一个item
     *
     * @param expandTabView   筛选控件
     * @param lists           list数据
     * @param defaultSelect   默认选择
     * @param defaultShowText 筛选title文字
     */
    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> lists, String defaultSelect, String defaultShowText) {
        PopOneListView popOneListView = new PopOneListView(this);
        popOneListView.setDefaultSelectByValue(defaultSelect);
        popOneListView.setCallBackAndData(lists, expandTabView, new PopOneListView.OnSelectListener() {
            @Override
            public void getValue(String key, String value) {
                Log.e("tag", "key :" + key + " ,value :" + value);
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popOneListView);
    }

    /**
     * 水平增加一个item
     *
     * @param expandTabView       筛选控件
     * @param parentLists         parent lists数据
     * @param childrenListLists   childrenListLists数据
     * @param defaultParentSelect parent 默认选择
     * @param defaultChildSelect  children 默认选择
     * @param defaultShowText     筛选title文字
     */
    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> parentLists,
                        List<ArrayList<KeyValueBean>> childrenListLists, String defaultParentSelect, String defaultChildSelect, String defaultShowText) {
        PopTwoListView popTwoListView = new PopTwoListView(this);
        popTwoListView.setDefaultSelectByValue(defaultParentSelect, defaultChildSelect);
        popTwoListView.setCallBackAndData(expandTabView, parentLists, childrenListLists, new PopTwoListView.OnSelectListener() {
            @Override
            public void getValue(String showText, String parentKey, String childrenKey) {
                Log.e("tag", "showText :" + showText + " ,parentKey :" + parentKey + " ,childrenKey :" + childrenKey);
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popTwoListView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (expandTabView != null) {
            expandTabView.onExpandPopView();
        }
    }

    private void setConfigsDatas() {
        try {
            InputStream is = getAssets().open("searchType");
            String searchTypeJson = readStream(is);
            ConfigsMessageDTO messageDTO = JSONObject.parseObject(searchTypeJson, ConfigsMessageDTO.class);
            ConfigsDTO configsDTO = messageDTO.getInfo();

            mPriceLists = configsDTO.getPriceType();
            mSortLists = configsDTO.getSortType();
            mFavorLists = configsDTO.getSortType();

            List<ConfigAreaDTO> configAreaListDTO = configsDTO.getCantonAndCircle();
            for (ConfigAreaDTO configAreaDTO : configAreaListDTO) {
                KeyValueBean keyValueBean = new KeyValueBean();
                keyValueBean.setKey(configAreaDTO.getKey());
                keyValueBean.setValue(configAreaDTO.getValue());
                mParentLists.add(keyValueBean);

                ArrayList<KeyValueBean> childrenLists = new ArrayList<>();
                for (KeyValueBean keyValueBean1 : configAreaDTO.getBusinessCircle()) {
                    childrenLists.add(keyValueBean1);
                }
                mChildrenListLists.add(childrenLists);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}

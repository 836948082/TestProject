package com.runtai.testproject;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.runtai.grantandroidpermission.CheckPermission;
import com.runtai.grantandroidpermission.listeners.PermissionListener;
import com.runtai.grantandroidpermission.utils.PermissionUtils;
import com.runtai.logger.Logger;
import com.runtai.testproject.cehua.BaseActivity;
import com.runtai.testproject.utils.StringUtil;
import com.runtai.testproject.utils.ToastUtil;

/**
 * 作者：高炎鹏
 * 时间：2016/10/20 09:34
 * 描述：选择联系人界面
 */
public class ContactsActivity extends BaseActivity implements View.OnClickListener {

    private static final int PICK_CONTACT_REQUEST = 0;
    private EditText contacts_et;
    private Button contacts_bt;
    private String[] permissionContacts = new String[]{Manifest.permission.READ_CONTACTS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
//        setSwipeBackEnable(false);
        initView();
    }

    public void initView() {
        contacts_et = (EditText) findViewById(R.id.contacts_et);
        contacts_et.setOnClickListener(this);
        contacts_bt = (Button) findViewById(R.id.contacts_bt);
        contacts_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contacts_et:
                break;
            case R.id.contacts_bt:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (PermissionUtils.hasSelfPermissions(ContactsActivity.this, permissionContacts)) {
                        choose();
                        Log.e("6.0有权限", "跳转");
                    } else {
                        CheckPermission
                                .from(ContactsActivity.this)
                                .setPermissions(permissionContacts)
                                .setRationaleMsg("该功能需要读取联系人，不开启将无法正常工作！")
                                .setPermissionListener(new PermissionListener() {
                                    @Override
                                    public void permissionGranted() {
                                        Log.e("6.0--权限", "开启权限成功");
                                        choose();
                                    }
                                    @Override
                                    public void permissionDenied() {
                                        Log.e("6.0--权限", "开启权限失败");
                                    }
                                })
                                .check();
                        Log.e("6.0没有权限", "不跳转页面");
                    }
                } else {
                    choose();
                    Log.e("不用申请权限", "android 小于 6.0");
                }
                break;
            default:
                break;
        }
    }

    public void choose() {
        Logger.e(Build.BRAND);
        if ("Meizu".equals(Build.BRAND)) {
            choose_mz();
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
            startActivityForResult(intent, PICK_CONTACT_REQUEST);
        }
    }

    /**
     * 自定义显示Contacts提供的联系人的方法
     */
    public void choose_mz() {
        //生成ContentResolver对象
        ContentResolver contentResolver = getContentResolver();
        // 获得所有的联系人
         /*Cursor cursor = contentResolver.query(
                 ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
          */
        //这段代码和上面代码是等价的，使用两种方式获得联系人的Uri
        Cursor cursor = contentResolver.query(Uri.parse("content://com.android.contacts/contacts"), null, null, null, null);

        if (cursor == null) {
            return;
        }
        // 循环遍历
        if (cursor.moveToFirst()) {
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            int displayNameColumn = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

            do {
                // 获得联系人的ID
                String contactId = cursor.getString(idColumn);
                // 获得联系人姓名
                String displayName = cursor.getString(displayNameColumn);

                Logger.e("联系人姓名：" + displayName);
                // 查看联系人有多少个号码，如果没有号码，返回0
                int phoneCount = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (phoneCount > 0) {
                    // 获得联系人的电话号码列表
                    Cursor phoneCursor = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
                    if (phoneCursor == null) {
                        return;
                    }
                    if (phoneCursor.moveToFirst()) {
                        int num = 0;
                        do {
                            //遍历所有的联系人下面所有的电话号码
                            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            //使用Toast技术显示获得的号码
                            Logger.e("联系人电话：" + phoneNumber);
                            num++;
                        } while (phoneCursor.moveToNext());
                        Logger.e(num + "个号码");
                    }
                }
            } while (cursor.moveToNext());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                Cursor cursor = getContentResolver().query(contactData, projection, null, null, null);
                cursor.moveToFirst();
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column).trim();

                // 如果有+86把他去掉
                if (number.contains("+86")) {
                    number = number.substring(3);
                }
                // 只过滤出数字
                String num = getNum(number);
                // 匹配正则
                if (StringUtil.checkMobilephone(num)) {
                    contacts_et.setText(num);
                    Logger.e(num);
                    setEditSelectionLoc(contacts_et, contacts_et.getText().length());
                } else {
                    contacts_et.setText("");
                    ToastUtil.showShort(ContactsActivity.this, "只能识别11位手机号码!");
                }
            }
        }
    }

    /**
     * 过滤出一段字符串中的所有数字
     */
    public String getNum(String str) {
        String num = "";
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                    num += str.charAt(i);
                }
            }
        }
        return num;
    }

    /**
     * 设置光标位置
     */
    public void setEditSelectionLoc(EditText edit, int index) {
        edit.setSelection(index);
    }

    /**
     * 获取光标当前位置
     */
    public int getEditSelection(EditText edit) {
        return edit.getSelectionStart();
    }

}

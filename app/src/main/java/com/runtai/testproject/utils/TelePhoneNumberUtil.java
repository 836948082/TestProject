package com.runtai.testproject.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * @作者：高炎鹏
 * @日期：2016/12/14时间09:22
 * @描述：联系人工具
 */
public class TelePhoneNumberUtil {

    /**
     * 删除所有通讯录内容
     */
    public static void DeleteAllTXL(Context context) {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID}, null,
                null, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            // 根据id删除data中的相应数据
            resolver.delete(uri, null, null);
            uri = Uri.parse("content://com.android.contacts/data");
            resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
        }
    }

    /**
     * 根据姓名删除通讯录内容
     */
    public static void DeleteAllTXLofName(Context context, String name) {
        // 根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID},
                "display_name=?", new String[]{name}, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            // 根据id删除data中的相应数据
            resolver.delete(uri, "display_name=?", new String[]{name});
            uri = Uri.parse("content://com.android.contacts/data");
            resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
        }
    }

    /**
     * 读取通讯录中全部的联系人全部信息
     */
    public static void ReadAllTXL(Context context) {
        // 需要先在raw_contact表中遍历id，并根据id到data表中获取数据
        Uri uri = Uri.parse("content://com.android.contacts/contacts"); // 访问raw_contacts表
        ContentResolver resolver = context.getContentResolver();
        // 获得_id属性
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID}, null,
                null, null);
        while (cursor.moveToNext()) {
            StringBuilder buf = new StringBuilder();
            // 获得id并且在data中寻找数据
            int id = cursor.getInt(0);
            buf.append("id=" + id);
            uri = Uri.parse("content://com.android.contacts/contacts/" + id
                    + "/data");
            // data1存储各个记录的总数据，mimetype存放记录的类型，如电话、email等
            Cursor cursor2 = resolver.query(uri, new String[]{ContactsContract.Data.DATA1,
                    ContactsContract.Data.MIMETYPE}, null, null, null);
            while (cursor2.moveToNext()) {
                String data = cursor2
                        .getString(cursor2.getColumnIndex("data1"));
                if (cursor2.getString(cursor2.getColumnIndex("mimetype"))
                        .equals("vnd.android.cursor.item/name")) { // 如果是名字
                    buf.append(",name=" + data);
                } else if (cursor2
                        .getString(cursor2.getColumnIndex("mimetype")).equals(
                                "vnd.android.cursor.item/phone_v2")) { // 如果是电话
                    buf.append(",phone=" + data);
                } else if (cursor2
                        .getString(cursor2.getColumnIndex("mimetype")).equals(
                                "vnd.android.cursor.item/email_v2")) { // 如果是email
                    buf.append(",email=" + data);
                } else if (cursor2
                        .getString(cursor2.getColumnIndex("mimetype")).equals(
                                "vnd.android.cursor.item/postal-address_v2")) { // 如果是地址
                    buf.append(",address=" + data);
                } else if (cursor2
                        .getString(cursor2.getColumnIndex("mimetype")).equals(
                                "vnd.android.cursor.item/organization")) { // 如果是组织
                    buf.append(",organization=" + data);
                }
            }
            String str = buf.toString();
            Log.i("Contacts", str);
        }
    }

    /**
     * 根据姓名查找号码(支持一个姓名下多个号码)
     */
    public static void ReadByName(Context context, String name) {
        String phoneNumber;
        //使用ContentResolver查找联系人数据
        Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        //遍历查询结果，找到所需号码
        while (cursor.moveToNext()) {
            //获取联系人ID
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //获取联系人的名字
            String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            if (name.equals(contactName)) {
                //使用ContentResolver查找联系人的电话号码
                Cursor phone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                while (phone.moveToNext()) {
                    phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.e("根据姓名查找号码", "phone=" + phoneNumber);
                }
            }
        }
    }

    /**
     * 根据姓名查找号码(支持一个姓名下多个号码)
     */
    public static void selectOfName(Context context, String name) {
        // 根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID},
                "display_name=?", new String[]{name}, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            //使用ContentResolver查找联系人的电话号码
            Cursor phone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
            while (phone.moveToNext()) {
                String phoneNumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.e("根据姓名查找号码", "phone=" + phoneNumber);
            }
        }
    }

    /**
     * 根据电话号码获取相对应的联系人姓名
     *
     * @param context
     * @param phoneNum
     * @return String
     */
    public String getContactNameFromPhoneBook(Context context, String phoneNum) {
        String contactName = "";
        ContentResolver resolver = context.getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.NUMBER + " = ?", new String[]{phoneNum}, null);
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            cursor.close();
        }
        return contactName;
    }

}

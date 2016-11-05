package com.runtai.testproject.utils;

import android.content.ContentValues;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证正则表达式
 */
public class StringUtil {

    /**
     * 判断字符串是否为 null/只包含空格/空
     */
    public static boolean isBlank(String str) {
        if (null == str)
            return true;
        if ("".equals(str.trim()))
            return true;
        if (str.equals("null"))
            return true;
        return false;
    }

    /**
     * 判断两个字符串的值是否相等 (null和空字符串认为相等,忽略字符串前后空格)
     */
    public static boolean compareString(String str1, String str2) {
        if (null == str1) {
            str1 = "";
        }
        if (null == str2) {
            str2 = "";
        }
        if (str1.trim().equals(str2.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 将对象转成String
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString().trim();
    }

    public static String encodePassword(String password, String algorithm) {
        if (algorithm == null)
            return password;
        byte unencodedPassword[] = password.getBytes();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            return password;
        }
        md.reset();
        md.update(unencodedPassword);
        byte encodedPassword[] = md.digest();
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 16)
                buf.append("0");
            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String getEncryptPassword(String password) {
        try {
            //return Des.encrypt(password, KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public static String getEncryptPasswordMD5(String password) {
        return encodePassword(password, "MD5");
    }

    /**
     * 获取json节点值
     */
    public static String getJSONObject(JSONObject jsonObject, String jsonNode) {
        try {
            if (jsonObject.has(jsonNode))
                return jsonObject.get(jsonNode).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JSONObject getJSONNode(JSONObject jsonObject, String jsonNode) {
        try {
            if (jsonObject.has(jsonNode))
                return jsonObject.getJSONObject(jsonNode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 像数据库插入字段
     */
    public static ContentValues pubValues(ContentValues values, String cloumn, String str_value) {
        if (str_value != null) {
            values.put(cloumn, str_value);
        }
        return values;
    }

    /**
     * 字符串转整数
     */
    public static int strToInt(String l_ser) {
        return Integer.parseInt(l_ser);
    }

    /**
     * 字符串转double
     */
    public static double strToDouble(String gis) {
        return Double.parseDouble(gis);
    }

    /**
     * 字符串转long
     */
    public static long strToLong(String time) {
        return Long.parseLong(time);
    }

    /**
     * 验证手机电话号码
     */
    public static boolean checkMobilephone(String mobilephone) {
//		String cm = "^1(3[4-9]|4[7]|5[0-27-9]|7[08]|8[2-478])\\d{8}$";// 中国移动正则
//		String cu = "^1(3[0-2]|4[5]|5[256]|7[016]|8[56])\\d{8}$";// 中国联通正则
//		String ct = "^1(3[34]|53|7[07]|8[019])\\d{8}$";// 中国电信正则
//		if (Pattern.matches(cm, mobilephone) || Pattern.matches(cu, mobilephone) || Pattern.matches(ct, mobilephone)) {
//			return true;
//		}
        String telRegex = "^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$";
        if (Pattern.matches(telRegex, mobilephone)) {
            return true;
        }
        return false;
    }

    /**
     * 验证密码输入规则
     *
     * @return
     */
    public static boolean checkPassword(String password) {
        String telRegex = "^[\\x20-\\x7e]{6,20}$";
        if (Pattern.matches(telRegex, password)) {
            return true;
        }
        return false;
    }

    /**
     * 验证固定电话区号正则表达式(3-4位)
     */
    public static boolean checkquhao(String quhao) {
        String telRegex = "^(0[0-9]{2,3})$";
        if (Pattern.matches(telRegex, quhao)) {
            return true;
        }
        return false;
    }

    /**
     * 验证固定电话号码正则表达式(7-8位)
     */
    public static boolean checkguhua(String guhua) {
        String telRegex = "^([2-9][0-9]{6,7})$";
        if (Pattern.matches(telRegex, guhua)) {
            return true;
        }
        return false;
    }

    /**
     * 验证QQ正则表达式(5-11位)
     */
    public static boolean checkQQ(String QQ) {
        String telRegex = "^([1-9][0-9]{4,10})$";
        if (Pattern.matches(telRegex, QQ)) {
            return true;
        }
        return false;
    }

    /**
     * 验证水电气正则表达式(7-10位)
     */
    public static boolean checksdq(String sdq) {
        String telRegex = "^((\\d){7,10})$";
        if (Pattern.matches(telRegex, sdq)) {
            return true;
        }
        return false;
    }

    /**
     * 验证金额正则表达式(两位小数的非负浮点数输入)
     */
    public static boolean checkprice(String price) {
        String telRegex = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$";
        if (Pattern.matches(telRegex, price)) {
            return true;
        }
        return false;
    }

    /**
     * 判断email格式是否正确
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 验证身份证号
     */
    public static boolean isIDcard(String text) {
        String reg15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        String reg18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
        return text.matches(reg15) || text.matches(reg18);
    }

    /**
     * 转化时间字符串
     * 类型：\/Date(1395396358000)\/
     */
    public static String date(String date) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>?~！@#�?…�?&*（）—�?+|{}【�?‘；：�?“�?。，、？Date]";
        try {
            if (null == date || date.equals("")) {
                return "";
            } else {
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(date);
                System.out.println(m.replaceAll(""));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String sd = sdf.format(new Date(Long.parseLong(m.replaceAll("").trim())));
                return sd;
            }
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 是否包含特殊字符
     */
    public static boolean containsAny(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#�?%…�??&*（）—�??+|{}【�?��?�；：�?��?��?��?�，、？]";
        //System.out.println("++++++++++++++++++++++++++++++++"+str.contains(regEx));
        if (str != null) {
            Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(str);
            return m.find();
        } else {
            return false;
        }
    }

    /**
     * 半角转换为全角
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)

                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

}


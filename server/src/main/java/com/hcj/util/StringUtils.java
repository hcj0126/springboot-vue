package com.hcj.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtils
 *  字符串工具类
 * @author hcj
 * @date 2023-06-16
 */
public class StringUtils {
    public StringUtils() {
    }

    public static Boolean isNull(String str) {
        return str == null ? true : false;
    }

    public static Boolean isNotNull(String str) {
        Boolean flag = !isNull(str);
        return flag;
    }

    public static Boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return str.length() <= 0 ? true : false;
        }
    }

    public static Boolean isNotNullOrEmpty(String str) {
        Boolean flag = !isNullOrEmpty(str);
        return flag;
    }

    public static boolean isExit(String str, String flag) {
        return str.indexOf(flag) <= 0;
    }

    public static int length(String str) {
        return str == null ? 0 : str.length();
    }

    public static int getAsc(String str) {
        byte[] gc = str.getBytes();
        int ascNum = gc[0];
        return ascNum;
    }

    public static char backChar(int backnum) {
        char strChar = (char)backnum;
        return strChar;
    }

    public static String backStr(int backnum) {
        char strChar = (char)backnum;
        return String.valueOf(strChar);
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n|");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
            dest = dest.replace(backStr(160), "");
        }

        return dest;
    }

    public static boolean isLowerCase(String str) {
        boolean flag = false;
        if (null == str) {
            return false;
        } else {
            for(int i = 0; i < str.length(); ++i) {
                if (!Character.isLowerCase(str.charAt(i))) {
                    flag = false;
                    break;
                }

                flag = true;
            }

            return flag;
        }
    }

    public static boolean isUpperCase(String str) {
        boolean flag = false;
        if (null == str) {
            return false;
        } else {
            for(int i = 0; i < str.length(); ++i) {
                if (!Character.isUpperCase(str.charAt(i))) {
                    flag = false;
                    break;
                }

                flag = true;
            }

            return flag;
        }
    }
}

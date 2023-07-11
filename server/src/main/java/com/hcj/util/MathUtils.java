package com.hcj.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * MathUtils
 *  数学封装的工具类
 * @author hcj
 * @date 2023-06-16
 */
public class MathUtils {
    public MathUtils() {
    }

    public static String formatResult(BigDecimal value, String pattern) {
        return (new DecimalFormat(pattern)).format(value);
    }

    public static Double add(Double num1, Double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.add(b2).doubleValue();
    }

    public static Double sub(Double num1, Double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.subtract(b2).doubleValue();
    }

    public static Double mult(Double num1, Double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.multiply(b2).doubleValue();
    }

    public static Double div(Double num1, Double num2) {
        BigDecimal b1 = new BigDecimal(num1);
        BigDecimal b2 = new BigDecimal(num2);
        return b1.divide(b2).doubleValue();
    }
}

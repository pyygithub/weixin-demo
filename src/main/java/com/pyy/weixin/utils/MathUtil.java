/**
 * Copyright (C), 2015-2018, XXX有限公司
 * 项目名称:
 * 文件名称:
 * 作者: wolf
 * 日期: 2018/7/11 23:04
 * 描述:
 * 版本: V1.0
 */
package com.pyy.weixin.utils;

/**
 *  数字操作工具类
 *
 * @author wolf
 * @create 2018/7/11
 * @since 1.0.0
 */
public class MathUtil {

    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较两个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs((d1 - d2));
        if (result < MONEY_RANGE) {
            return true;
        }else {
            return false;
        }
    }

}
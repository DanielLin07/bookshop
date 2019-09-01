package com.daniellin07.bookshop.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 常用的一些验证，如手机、数字等
 *
 * @author DanielLin07
 * @date 2019/6/2 13:27
 */
public class ValidateUtil {

    /**
     * 手机号规则
     */
    private static final String MOBILE_PATTERN = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))(\\d{8})$";
    /**
     * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173
     **/
    private static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|53|7[37]|8[019])\\d{8}$)|(?:^(?:\\+86)?1700\\d{7}$)";
    /**
     * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709,175
     **/
    private static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[5]|5[56]|7[56]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[7-9]\\d{7}$)";
    /**
     * 中国移动号码格式验证 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
     **/
    private static final String CHINA_MOVE_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(?:^(?:\\+86)?1705\\d{7}$)";
    /**
     * 密码规则（6-16位字母、数字）
     */
    private static final String PASSWORD_PATTERN = "^[0-9A-Za-z]{6,16}$";
    /**
     * 固号（座机）规则
     */
    private static final String LANDLINE_PATTERN = "^(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?$";
    /**
     * 邮政编码规则
     */
    private static final String POSTCODE_PATTERN = "[1-9]\\d{5}";
    /**
     * 邮箱规则
     */
    private static final String EMAIL_PATTERN = "^([a-z0-9A-Z]+[-|_.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 年龄规则 1-120之间
     */
    private static final String AGE_PATTERN = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
    /**
     * 身份证规则
     */
    private static final String IDCARD_PATTERN = "^\\d{15}|\\d{18}$";
    /**
     * URL规则，http、www、ftp
     */
    private static final String URL_PATTERN = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    /**
     * QQ规则
     */
    private static final String QQ_PATTERN = "^[1-9][0-9]{4,13}$";
    /**
     * 全汉字规则
     */
    private static final String CHINESE_PATTERN = "^[\u4E00-\u9FA5]+$";
    /**
     * 全字母规则
     */
    private static final String STR_ENG_PATTERN = "^[A-Za-z]+$";
    /**
     * 整数规则
     */
    private static final String INTEGER_PATTERN = "^-?[0-9]+$";
    /**
     * 正整数规则
     */
    private static final String POSITIVE_INTEGER_PATTERN = "^\\+?[1-9][0-9]*$";

    /**
     * 验证手机号码格式
     *
     * @param mobile 手机号码
     * @return boolean
     */
    public static boolean validateMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return Boolean.FALSE;
        }
        return mobile.matches(MOBILE_PATTERN);
    }

    /**
     * 验证是否是电信手机号,133、153、180、189、177
     *
     * @param mobile 手机号
     * @return boolean
     */
    public static boolean validateTelecom(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return Boolean.FALSE;
        }
        return mobile.matches(CHINA_TELECOM_PATTERN);
    }

    /**
     * 验证是否是联通手机号 130,131,132,155,156,185,186,145,176,1707,1708,1709,175
     *
     * @param mobile 电话号码
     * @return boolean
     */
    public static boolean validateUnionMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return Boolean.FALSE;
        }
        return mobile.matches(CHINA_UNICOM_PATTERN);
    }

    /**
     * 验证是否是移动手机号
     *
     * @param mobile 手机号 134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
     * @return boolean
     */
    public static boolean validateMoveMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return Boolean.FALSE;
        }
        return mobile.matches(CHINA_MOVE_PATTERN);
    }

    /**
     * 验证密码格式：6-16 位字母、数字
     *
     * @param pwd 密码
     * @return boolean
     */
    public static boolean validatePwd(String pwd) {
        if (StringUtils.isEmpty(pwd)) {
            return Boolean.FALSE;
        }
        return Pattern.matches(PASSWORD_PATTERN, pwd);
    }

    /**
     * 验证座机号码，格式如：58654567,023-58654567
     *
     * @param landline 固话、座机
     * @return boolean
     */
    public static boolean validateLandLine(final String landline) {
        if (StringUtils.isEmpty(landline)) {
            return Boolean.FALSE;
        }
        return landline.matches(LANDLINE_PATTERN);
    }

    /**
     * 验证邮政编码
     *
     * @param postCode 邮政编码
     * @return boolean
     */
    public static boolean validatePostCode(final String postCode) {
        if (StringUtils.isEmpty(postCode)) {
            return Boolean.FALSE;
        }
        return postCode.matches(POSTCODE_PATTERN);
    }

    /**
     * 验证邮箱（电子邮件）
     *
     * @param email 邮箱（电子邮件）
     * @return boolean
     */
    public static boolean validateEmail(final String email) {
        if (StringUtils.isEmpty(email)) {
            return Boolean.FALSE;
        }
        return email.matches(EMAIL_PATTERN);
    }

    /**
     * 判断年龄，1-120之间
     *
     * @param age 年龄
     * @return boolean
     */
    public static boolean validateAge(final String age) {
        if (StringUtils.isEmpty(age)) {
            return Boolean.FALSE;
        }
        return age.matches(AGE_PATTERN);
    }

    /**
     * 身份证验证
     *
     * @param idCard 身份证
     * @return boolean
     */
    public static boolean validateIDCard(final String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return Boolean.FALSE;
        }
        return idCard.matches(IDCARD_PATTERN);
    }

    /**
     * URL地址验证
     *
     * @param url URL地址
     * @return boolean
     */
    public static boolean validateUrl(final String url) {
        if (StringUtils.isEmpty(url)) {
            return Boolean.FALSE;
        }
        return url.matches(URL_PATTERN);
    }

    /**
     * 验证QQ号
     *
     * @param qq QQ号
     * @return boolean
     */
    public static boolean validateQq(final String qq) {
        if (StringUtils.isEmpty(qq)) {
            return Boolean.FALSE;
        }
        return qq.matches(QQ_PATTERN);
    }

    /**
     * 验证字符串是否全是汉字
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean validateChinese(final String str) {
        if (StringUtils.isEmpty(str)) {
            return Boolean.FALSE;
        }
        return str.matches(CHINESE_PATTERN);
    }

    /**
     * 判断字符串是否全字母
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean validateStrEnglish(final String str) {
        if (StringUtils.isEmpty(str)) {
            return Boolean.FALSE;
        }
        return str.matches(STR_ENG_PATTERN);
    }

    /**
     * 判断是否是整数，包括负数
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean validateInteger(final String str) {
        if (StringUtils.isEmpty(str)) {
            return Boolean.FALSE;
        }
        return str.matches(INTEGER_PATTERN);
    }

    /**
     * 判断是否是大于0的正整数
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean validatePositiveInt(final String str) {
        if (StringUtils.isEmpty(str)) {
            return Boolean.FALSE;
        }
        return str.matches(POSITIVE_INTEGER_PATTERN);
    }
}

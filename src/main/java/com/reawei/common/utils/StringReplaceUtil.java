package com.reawei.common.utils;

/**
 * Created by xingwu on 2017/4/1.
 */
public class StringReplaceUtil {

    /**
     * 根据用户名的不同长度，来进行替换 ，达到保密效果
     *
     * @param userName 用户名
     * @return 替换后的用户名
     */
    public static String userNameReplaceWithStar(String userName) {
        String userNameAfterReplaced = "";

        if (userName == null) {
            userName = "";
        }

        int nameLength = userName.length();
        System.out.println(nameLength);

        if (nameLength <= 1) {
            userNameAfterReplaced = "*";

        } else if (nameLength == 2) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\w{0})\\w(?=\\w{1})");

        } else if (nameLength >= 3 && nameLength <= 6) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{1})\\d(?=\\d{1})");

        } else if (nameLength == 7) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{1})\\d(?=\\d{2})");

        } else if (nameLength == 8) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{2})\\d(?=\\d{2})");

        } else if (nameLength == 9) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{2})\\d(?=\\d{3})");

        } else if (nameLength == 10) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{3})\\d(?=\\d{3})");

        } else if (nameLength >= 11) {
            userNameAfterReplaced = replaceAction(userName, "(?<=\\d{3})\\d(?=\\d{4})");

        }
        return userNameAfterReplaced;
    }

    /**
     * 实际替换动作
     *
     * @param username username
     * @param regular  正则
     * @return
     */

    private static String replaceAction(String username, String regular) {
        return username.replaceAll(regular, "*");

    }

    /**
     * 身份证号替换，保留前四位和后四位
     * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param idCard 身份证号
     * @return
     */

    public static String idCardReplaceWithStar(String idCard) {
        if (idCard.isEmpty() || idCard == null) {
            return null;

        } else {
            return replaceAction(idCard, "(?<=\\d{4})\\d(?=\\d{4})");
        }
    }

    /**
     * 银行卡替换，保留后四位
     * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param bankCard 银行卡号
     * @return
     */

    public static String bankCardReplaceWithStar(String bankCard) {

        if (bankCard.isEmpty() || bankCard == null) {
            return null;

        } else {
            return replaceAction(bankCard, "(?<=\\d{0})\\d(?=\\d{4})");

        }

    }

    /**
     * 手机号替换 ，保留前三位和后四位
     * 如果手机号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param phone 手机号
     * @return
     */
    public static String phoneReplaceWithStar(String phone) {

        if (phone.isEmpty() || phone == null) {
            return null;

        } else {
            return replaceAction(phone, "(?<=[\\d]{3})\\d(?=[\\d]{4})");

        }

    }

    public static void main(String[] args) {
        String s = "15678901234";
        String phone = phoneReplaceWithStar(s);
        System.out.println(phone);
        String card = "360428199301074312";
        String idCard = idCardReplaceWithStar(card);
        System.out.println(idCard);
    }

}

package com.bright.bookstore.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author 徐亮亮
 * @since 2020/11/25
 */
public class AuthenticateUtils {

    /**
     * 检验密码
     *
     * @param rawPassword 原始密码
     * @param password    加密后的密码
     * @return 是否验证通过
     */
    public static boolean checkPassword(String rawPassword, String password) {
        String[] target = password.split("\\$");
        int allowedLength = 3;
        if (target.length != allowedLength) {
            return false;
        }
        int count = Integer.parseInt(target[0]);
        String salt = target[1];
        String hashPassword = target[2];
        if ("".equals(hashPassword) || "".equals(salt)) {
            return false;
        }

        String check = makePassword(rawPassword, salt, count);
        return password.equals(check);
    }

    /**
     * 生成加密后的密码
     * 迭代次数$盐$哈希
     * 以`$`作为分隔符
     *
     * @param rawPassword 原密码
     * @param salt        盐
     * @param count       迭代次数
     * @return 加密后的密码 : count$salt$hash
     */
    public static String makePassword(String rawPassword, String salt, int count) {
        byte[] rawBytes = rawPassword.getBytes();
        byte[] saltBytes = salt.getBytes();
        String allowedChars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789+-*/<>=";
        String targetString = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            for (int i = 0; i < count; i++) {
                digest.update(saltBytes);
            }
            // 32位长度的byte数组
            byte[] targetBytes = digest.digest(rawBytes);
            StringBuilder builder = new StringBuilder();
            for (byte b : targetBytes) {
                int index = (128 + b) % allowedChars.length();
                builder.append(allowedChars.charAt(index));
            }
            targetString = builder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.format("%s$%s$%s", count, salt, targetString);
    }

    /**
     * 生成加密后的密码
     * 迭代次数$盐$哈希
     * 以`$`作为分隔符
     *
     * @param rawPassword 原密码
     * @param salt        盐
     * @return 加密后的密码 : count$salt$hash
     */
    public static String makePassword(String rawPassword, String salt) {
        Random random = new Random();
        // 生成 [100000, 200000) 之间的随机整数
        int randomCount = (int) ((random.nextDouble() + 1) * 1e5);
        return makePassword(rawPassword, salt, randomCount);
    }

    /**
     * 生成加密后的密码
     * 迭代次数$盐$哈希
     * 以`$`作为分隔符
     *
     * @param rawPassword 原密码
     * @return 加密后的密码 : count$salt$hash
     */
    public static String makePassword(String rawPassword) {
        String randomSalt = getRandomString(12);
        return makePassword(rawPassword, randomSalt);
    }

    /**
     * 随机生成指定长度的字符串
     *
     * @param length       需要指定字符串的长度
     * @param allowedChars 字符集合的字符串
     * @return 随机字符串
     */
    public static String getRandomString(int length, String allowedChars) {
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            randomString.append(allowedChars.charAt(index));
        }
        return randomString.toString();
    }

    /**
     * 随机生成指定长度的字符串
     *
     * @param length 需要指定字符串的长度
     * @return 随机字符串
     */
    public static String getRandomString(int length) {
        String allowedChars = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
        return getRandomString(length, allowedChars);
    }

    public static void main(String[] args) {
        String rawPassword = "This is my password";
        String password = makePassword(rawPassword);

        System.out.println("原始的密码 " + rawPassword);
        System.out.println("加密后密码 " + password);

        // true 为验证通过，false 为验证失败
        if (checkPassword(rawPassword, password)) {
            System.out.println("验证通过");
        } else {
            System.out.println("密码错误");
        }

        String badPassword = "This isn`t my password";
        if (checkPassword(badPassword, password)) {
            System.out.println("验证通过");
        } else {
            System.out.println("密码错误");
        }
    }
}

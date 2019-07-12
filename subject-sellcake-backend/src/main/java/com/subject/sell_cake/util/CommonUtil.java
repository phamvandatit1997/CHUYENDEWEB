package com.subject.sell_cake.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Van Dat
 */
public class CommonUtil {

    public static final Random RANDOM = new SecureRandom();
    public final static ObjectMapper mapper = new ObjectMapper();

    public static boolean isValidPattern(String str, String regex) {

        if (regex == null) {
            throw new IllegalArgumentException("Regex pattern must not be null");
        }

        if (!StringUtils.isEmpty(str)) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(str);
            return matcher.matches();
        }

        return false;
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static Random rnd = new Random();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static boolean isBetweenRange(Number number, Number min, Number max) {

        if (number == null || (min == null && max == null) || (max != null && min != null && max.doubleValue() < min.doubleValue())) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        return !((min == null && number.doubleValue() > max.doubleValue())
                || (max == null && number.doubleValue() < min.doubleValue())
                || (min != null && max != null && (number.doubleValue() < min.doubleValue() || number.doubleValue() > max.doubleValue())));
    }

    @Deprecated
    public static boolean isSystemAdmin(String userId) {
        return (userId != null && userId.equals(Constant.SYSTEM_ADMIN_ID));
    }


    public static boolean isLengthBetween(String str, int min, int max) {

        if (min < 0 || max < min) {
            throw new IllegalArgumentException("The min value must be greater than or equal to 0 and less than the max value");
        }

        return (str != null && str.length() > 0 && str.length() < max);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateSalt() {
        byte[] salt = new byte[Constant.SALT_LENGTH];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static boolean isEmailFormat(String valueToValidate) {
        // Regex
        String regexExpression = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?";
        Pattern regexPattern = Pattern.compile(regexExpression);
        boolean valid = false;
        if (valueToValidate != null) {
            Matcher matcher = regexPattern.matcher(valueToValidate);
            valid = matcher.matches();
        } else { // The case of empty Regex expression must be accepted
            Matcher matcher = regexPattern.matcher("");
            valid = matcher.matches();
        }
        return valid;
    }

    public static boolean isFileNameFormat(String fileName) {
        boolean valid = false;
        if (fileName.startsWith("!o!")) {
            if (!(fileName.contains("\\")
                    || fileName.contains("?")
                    || fileName.contains("<")
                    || fileName.contains(">")
                    || fileName.contains("/")
                    || fileName.contains(":")
                    || fileName.contains("|")
                    || fileName.contains("\""))) {
                valid = true;
            }
        }
        return valid;
    }
    public static String getFileExtension(MultipartFile file) {
        String name = file.getOriginalFilename();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
}

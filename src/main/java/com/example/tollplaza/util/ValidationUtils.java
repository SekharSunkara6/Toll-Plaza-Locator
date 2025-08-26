package com.example.tollplaza.util;

public class ValidationUtils {

    public static boolean isValidPincode(String pincode) {
        if (pincode == null)
            return false;
        return pincode.matches("\\d{6}");
    }
}

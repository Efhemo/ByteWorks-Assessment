package com.efhem.byteworksassessment.util

import java.util.regex.Pattern

object Utils {

    fun isEmailValid(email: String): Boolean {
        val emailRegex =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+")
        return emailRegex.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        // Minumum of One Lowercase, Uppercase and Digit
        val passwordRegex = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")
        return passwordRegex.matcher(password).matches()
    }
}
package com.example.quantica.Classes

import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.example.quantica.R
import com.google.android.material.textfield.TextInputLayout
import java.util.*
import java.util.regex.Pattern

fun validateConfirmPassword(password: String, confirmPassword: String, etConfirmPasswordLayout: TextInputLayout): Boolean {
    var confirmPasswordBool = false
    if (confirmPassword.isEmpty()){
        etConfirmPasswordLayout.error = validateEmpty()

    }else if (password != confirmPassword){
        if (Locale.getDefault().displayLanguage == "español") {
            etConfirmPasswordLayout.error = "Ambas contraseñas ingresadas no son iguales. Por favor intente de nuevo"
        } else if (Locale.getDefault().displayLanguage == "English" || Locale.getDefault().displayLanguage != "English") {
            etConfirmPasswordLayout.error =  "Both passwords are not the same. Please try again"
        }
    }else{
        confirmPasswordBool = true
        etConfirmPasswordLayout.error = null
    }

    return confirmPasswordBool
}

fun validatePassword(password: String, etPasswordLayout: TextInputLayout): Boolean {
    var passwordBool = false

    val passwordRegex = Pattern.compile(
        "^" +           // start-of-string
                "(?=.*[0-9])" +      // a digit must occur at least once
                "(?=.*[a-z])" +      // a lower case letter must occur at least once
                "(?=.*[A-Z])" +      // an upper case letter must occur at least once
                "(?=\\S+$)" +        // no whitespace allowed in the entire string
                ".{8,}" +            // anything, at least six places though
                "$"                  // end-of-string
    )
    if (password.isEmpty()){

        etPasswordLayout.error = validateEmpty()

    }else if (!passwordRegex.matcher(password).matches()){
        if (Locale.getDefault().displayLanguage == "español") {
            etPasswordLayout.error = "La contraseña es muy debil. Debe tener al menos:\n " +
                    "- 8 Caracteres sin espacios en blanco\n " +
                    "- 1 Mayuscula y Minuscula\n " +
                    "- 1 digito"
        } else if (Locale.getDefault().displayLanguage == "English" || Locale.getDefault().displayLanguage != "English") {
            etPasswordLayout.error = "Password is too week. it must have:\n" +
                    "- 8 Characters without white spaces\n" +
                    "- 1 Upper case and Lower case\n" +
                    "- 1 Digit"
        }
    }else{
        passwordBool = true
        etPasswordLayout.error = null
    }

    return passwordBool
}

fun validateEmail(email: String, etEmailLayout: TextInputLayout): Boolean {
    var emailBool = false
    if (email.isEmpty()){

        etEmailLayout.error = validateEmpty()

    }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
        if (Locale.getDefault().displayLanguage == "español") {
            etEmailLayout.error = "Por favor ingrese un email valido"
        } else if (Locale.getDefault().displayLanguage == "English" || Locale.getDefault().displayLanguage != "English") {
            etEmailLayout.error =  "Please enter a valid email address"
        }
    }else{
        emailBool = true
        etEmailLayout.error = null
    }

    return emailBool
}

fun validateUser(user: String, etUserLayout: TextInputLayout): Boolean {
    var userbool = false

    val userRegex = Pattern.compile(
            "^" +
                "[a-zA-Z0-9_-]"+
                "{8,}$"
    )

    if (user.isEmpty()){
        etUserLayout.error =  validateEmpty()
    }else if (!userRegex.matcher(user).matches()){
        if (Locale.getDefault().displayLanguage == "español") {
            etUserLayout.error = "Su nombre de usuario debe tener al menos 8 caracteres y sin espacios en blanco. puede usar '_' o '-' para unir"
        } else if (Locale.getDefault().displayLanguage == "English" || Locale.getDefault().displayLanguage != "English") {
            etUserLayout.error =  "User name must have at least 8 characters without white spaces. you can use '_' o '-' to connect"
        }
    }else{
        userbool = true
        etUserLayout.error = null
    }

    return userbool
}

fun validateEmpty(): String{
    var text = ""
    if (Locale.getDefault().displayLanguage == "español") {
        text = "No puede dejar este campo vacio"
    } else if (Locale.getDefault().displayLanguage == "English" || Locale.getDefault().displayLanguage != "English") {
        text = "Field can not be empty"
    }
    return text
}

fun validatePasswordSingIn(password: String, etPasswordLayout: TextInputLayout): Boolean {
    var passwordBool = false

    val passwordRegex = Pattern.compile(
        "^" +           // start-of-string
                "(?=.*[0-9])" +      // a digit must occur at least once
                "(?=.*[a-z])" +      // a lower case letter must occur at least once
                "(?=.*[A-Z])" +      // an upper case letter must occur at least once
                "(?=\\S+$)" +        // no whitespace allowed in the entire string
                ".{8,}" +            // anything, at least six places though
                "$"                  // end-of-string
    )
    if (password.isEmpty()){

        etPasswordLayout.error = validateEmpty()

    }else if (!passwordRegex.matcher(password).matches()){
        if (Locale.getDefault().displayLanguage == "español") {
            etPasswordLayout.error = "Este no es una contraseña valida"
        } else if (Locale.getDefault().displayLanguage == "English" || Locale.getDefault().displayLanguage != "English") {
            etPasswordLayout.error = "This password is not valid"
        }
    }else{
        passwordBool = true
        etPasswordLayout.error = null
    }

    return passwordBool
}
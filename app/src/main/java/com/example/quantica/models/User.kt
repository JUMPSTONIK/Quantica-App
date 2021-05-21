package com.example.quantica.models

data class User(
    var id_user : Int = 0,
    var nickname: String? = null,
    var email: String? = null,
    var password: String? = null
)
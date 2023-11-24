package com.example.filmify.data

data class UserLoginInfo(
    val user_email: String,
    val password: String,
    val gender: String,
    val birthday: String,
    val registration_date: String,
    val is_admin: Boolean
)
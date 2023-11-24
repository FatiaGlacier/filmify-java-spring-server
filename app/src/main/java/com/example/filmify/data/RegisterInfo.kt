package com.example.filmify.data

import java.math.BigInteger

data class RegisterInfo(val user_id: BigInteger,
                    val user_name: String,
                    val user_email: String,
                    val password: String,
                    val gender: String,
                    val birthday: String,
                    val registration_date: String,
                    val is_admin: Boolean)
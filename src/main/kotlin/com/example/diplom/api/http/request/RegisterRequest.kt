package com.example.diplom.api.http.request

import java.time.LocalDate

/**
 * @author kimetsu - 02.06.2024 - 15:37
 */
data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val patronymic: String?,
    val position: Long,
    val phone: String,
    val email: String,
    val passport: String,
    val birthdate: LocalDate,
    val active: Boolean,
    val password: String,
)

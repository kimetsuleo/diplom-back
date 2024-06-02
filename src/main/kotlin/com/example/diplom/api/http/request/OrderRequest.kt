package com.example.diplom.api.http.request

import java.time.LocalDate

/**
 * @author kimetsu - 02.06.2024 - 18:21
 */
data class OrderRequest(
    val subject:String,
    val date: LocalDate,
    val responded: Long
)
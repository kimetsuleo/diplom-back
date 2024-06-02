package com.example.diplom.api.http.request

/**
 * @author kimetsu - 02.06.2024 - 17:30
 */
data class ContractRequest(
    val employee: Long,
    val position: Long,
    val rate: Double,
)
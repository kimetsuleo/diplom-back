package com.example.diplom.api.http.response

import com.example.diplom.domain.Position
import java.time.LocalDateTime

/**
 * @author kimetsu - 03.06.2024 - 18:34
 */
data class EmployeeResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val position: Position,
    val day: ShiftResponse
)

data class ShiftResponse(
    val day: String,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
)
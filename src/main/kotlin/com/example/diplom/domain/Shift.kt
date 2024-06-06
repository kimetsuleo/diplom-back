package com.example.diplom.domain

import jakarta.persistence.*
import java.time.DayOfWeek
import java.time.LocalDateTime

/**
 * @author kimetsu - 02.06.2024 - 19:40
 */
@Entity
@Table(name = "shifts")
data class Shift(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_shift")
    @SequenceGenerator(name = "s_shift", sequenceName = "s_shift", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long?,

    @Enumerated(EnumType.STRING)
    val day: DayOfWeek,

    @Column(name = "start_at", nullable = false)
    val startAt: LocalDateTime,

    @Column(name = "end_at", nullable = false)
    val endAt: LocalDateTime,
)

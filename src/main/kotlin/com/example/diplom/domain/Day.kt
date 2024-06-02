package com.example.diplom.domain

import jakarta.persistence.*

/**
 * @author kimetsu - 02.06.2024 - 19:07
 */
@Entity
@Table(name = "days")
data class Day(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_day")
    @SequenceGenerator(name = "s_day", sequenceName = "s_day", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long?,

    @Column(name = "name", nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: DayType,
)

enum class DayType {
    WORKER,
    DAYOFF
}
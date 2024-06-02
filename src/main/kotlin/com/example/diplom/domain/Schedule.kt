package com.example.diplom.domain

import jakarta.persistence.*
import java.time.LocalDate

/**
 * @author kimetsu - 02.06.2024 - 19:38
 */
@Entity
@Table(name = "schedules")
data class Schedule(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_schedule")
    @SequenceGenerator(name = "s_schedule", sequenceName = "s_schedule", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long?,

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    val employee: Employee,

    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    val shift: Shift,

    @Column(name = "date_at", nullable = false)
    val dateAt: LocalDate,
)
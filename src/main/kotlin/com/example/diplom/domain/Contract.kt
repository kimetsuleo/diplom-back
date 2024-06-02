package com.example.diplom.domain

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 * @author kimetsu - 02.06.2024 - 13:25
 */
@Entity
@Table(name = "contracts")
data class Contract(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_contract")
    @SequenceGenerator(name = "s_contract", sequenceName = "s_contract", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long?,

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    var employee: Employee,

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    var position: Position,

    @Column(name = "rate", nullable = true)
    var rate: Double,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
    )
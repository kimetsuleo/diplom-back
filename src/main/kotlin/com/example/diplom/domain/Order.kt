package com.example.diplom.domain

import jakarta.persistence.*
import java.time.LocalDate

/**
 * @author kimetsu - 02.06.2024 - 13:25
 */
@Entity
@Table(name = "orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_order")
    @SequenceGenerator(name = "s_order", sequenceName = "s_order", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long?,

    @Column(name = "date_at")
    val date: LocalDate,

    @Column(name = "subject")
    val subject: String,

    @ManyToOne
    @JoinColumn(name = "responded_id")
    val responsible: Employee,

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    val respondent: Employee,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: OrderStatus
)
package com.example.diplom.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import java.time.LocalDate

/**
 * @author kimetsu - 02.06.2024 - 13:07
 */
@Entity
@Table(name = "employees")
data class Employee(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_employee")
    @SequenceGenerator(name = "s_employee", sequenceName = "s_employee", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long?,

    @Column(name = "first_name", nullable = false)
    val firstName: String,

    @Column(name = "last_name", nullable = false)
    val lastName: String,

    @Column(name = "patronymic", nullable = true)
    val patronymic: String?,

    @ManyToOne
    @JoinColumn(name = "position_id", nullable = false)
    val position: Position,

    @Column(name = "phone", nullable = false)
    val phone: String,

    @Column(name = "email", nullable = false, unique = true)
    val email: String,

    @Column(name = "passport", nullable = false, unique = true)
    val passport: String,

    @Column(name = "birthdate", nullable = true)
    val birthdate: LocalDate,

    @Column(name = "active", nullable = false)
    val active: Boolean,

    @Column(name = "password", nullable = false)
    val password: String,
)

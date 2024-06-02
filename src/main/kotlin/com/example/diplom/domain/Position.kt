package com.example.diplom.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority

/**
 * @author kimetsu - 02.06.2024 - 13:07
 */
@Entity
@Table(name = "positions")
data class Position(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_position")
    @SequenceGenerator(name = "s_position", sequenceName = "s_position", allocationSize = 1)
    @Column(name = "id", unique = true, nullable = false)
    val id: Long?,

    @Column(name = "name", nullable = false)
    val name: String
) : GrantedAuthority {
    override fun getAuthority(): String = name
}

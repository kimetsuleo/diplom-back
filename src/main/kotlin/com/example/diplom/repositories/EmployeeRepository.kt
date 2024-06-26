package com.example.diplom.repositories

import com.example.diplom.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author kimetsu - 02.06.2024 - 13:38
 */
interface EmployeeRepository: JpaRepository<Employee, Long> {
    fun findByEmail(email: String): Employee?

    fun findByPhone(phone: String): Employee?

    fun findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName: String, lastName: String): List<Employee>?

    fun findAllByFirstNameContainingIgnoreCase(firstName: String): List<Employee>?

    fun existsByEmail(email: String): Boolean

    fun existsByPhone(phone: String): Boolean

    fun existsByPassport(passport: String): Boolean
}
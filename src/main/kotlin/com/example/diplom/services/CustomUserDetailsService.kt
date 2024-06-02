package com.example.diplom.services

import com.example.diplom.domain.Employee
import com.example.diplom.repositories.EmployeeRepository
import com.example.diplom.repositories.PositionRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * @author kimetsu - 02.06.2024 - 14:52
 */
@Service
class CustomUserDetailsService(
    private val repository: EmployeeRepository,
    private val positionRepository: PositionRepository
) : UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        val employee = email?.let { repository.findByEmail(it) } ?: throw NotFoundException()

        return mapToUserDetails(employee)
    }

    private fun mapToUserDetails(employee: Employee): UserDetails {
        val positions = positionRepository.findFromEmployeeByEmail(employee.email)
        return User(
            employee.email,
            employee.password,
            positions
        )
    }
}
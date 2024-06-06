package com.example.diplom.utils

import com.example.diplom.domain.Employee
import com.example.diplom.repositories.EmployeeRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

/**
 * @author kimetsu - 02.06.2024 - 17:48
 */
@Service
class EmployeeContext(
    private val repository: EmployeeRepository
) {
    fun getEmployee(): Employee {
        val principal = SecurityContextHolder.getContext().authentication.principal
        return repository.findByEmail(principal.toString()) ?: throw NotFoundException()
    }
}



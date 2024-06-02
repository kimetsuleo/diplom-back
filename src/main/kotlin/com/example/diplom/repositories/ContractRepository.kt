package com.example.diplom.repositories

import com.example.diplom.domain.Contract
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author kimetsu - 02.06.2024 - 13:40
 */
interface ContractRepository: JpaRepository<Contract, Long> {

    fun findByIdAndEmployeeEmail(id: Long, email: String): Contract?

    fun findAllByEmployeeEmail(email: String): List<Contract>?
}
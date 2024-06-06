package com.example.diplom.services

import com.example.diplom.api.http.request.ContractRequest
import com.example.diplom.domain.Contract
import com.example.diplom.exceptions.NotFoundException
import com.example.diplom.repositories.ContractRepository
import com.example.diplom.repositories.EmployeeRepository
import com.example.diplom.repositories.PositionRepository
import com.example.diplom.utils.EmployeeContext
import org.springframework.stereotype.Service
import java.time.LocalDateTime

/**
 * @author kimetsu - 02.06.2024 - 17:27
 */
@Service
class ContractService(
    private val repository: ContractRepository,
    private val employeeRepository: EmployeeRepository,
    private val positionRepository: PositionRepository,
    private val context: EmployeeContext
) {

    fun getAll() =
        context.getEmployee().let { repository.findAllByEmployeeEmail(it.email) }

    fun getById(id: Long) =
        context.getEmployee().let { repository.findByIdAndEmployeeEmail(id, it.email) }

    fun create(req: ContractRequest) {
        val emp = employeeRepository.findById(req.employee).orElseThrow { throw NotFoundException() }
        val pos = positionRepository.findById(req.position).orElseThrow { throw NotFoundException() }

        return Contract(
            0,
            emp,
            pos,
            req.rate,
            LocalDateTime.now()
        ).run {
            repository.save(this)
        }
    }

    fun update(id: Long, req: ContractRequest): Contract? {
        val contr = repository.findById(id).orElseThrow { throw NotFoundException() }
        val emp = employeeRepository.findById(req.employee).orElseThrow { throw NotFoundException() }
        val pos = positionRepository.findById(req.position).orElseThrow { throw NotFoundException() }

        return contr.apply {
            employee = emp
            position = pos
            rate = req.rate
            repository.save(this)
        }
    }
}
package com.example.diplom.services

import com.example.diplom.api.http.request.OrderRequest
import com.example.diplom.domain.Order
import com.example.diplom.domain.OrderStatus
import com.example.diplom.repositories.EmployeeRepository
import com.example.diplom.repositories.OrderRepository
import com.example.diplom.utils.EmployeeContext
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

/**
 * @author kimetsu - 02.06.2024 - 18:20
 */
@Service
class OrderService(
    private val repository: OrderRepository,
    private val employeeRepository: EmployeeRepository,
    private val context: EmployeeContext
) {

    fun getAll() =
        context.getEmployee().let { repository.findAllByResponsibleEmail(it.email) }?.toList()

    fun getByIdAndResponsible(id: Long) =
        context.getEmployee().let { repository.findByIdAndResponsibleEmail(id, it.email) }

    fun getByIdAndRespondent(id: Long) =
        context.getEmployee().let { repository.findByIdAndRespondentEmail(id, it.email) }


    fun create(req: OrderRequest) {
        val responsible = context.getEmployee()
        val responded = employeeRepository.findById(req.responded).orElseThrow { throw NotFoundException() }
        return Order(
            0,
            req.date,
            req.subject,
            responsible,
            responded,
            OrderStatus.CREATED
        ).run {
            repository.save(this)
        }
    }

    fun approve(id: Long): Order? {
        val order = context.getEmployee().let { repository.findByIdAndRespondentEmail(id, it.email) }
        order?.apply { status = OrderStatus.SIGNED }
        return order?.let { repository.save(it) }
    }

}
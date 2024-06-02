package com.example.diplom.services

import com.example.diplom.api.http.request.OrderRequest
import com.example.diplom.domain.Order
import com.example.diplom.domain.OrderStatus
import com.example.diplom.repositories.EmployeeRepository
import com.example.diplom.repositories.OrderRepository
import com.example.diplom.utils.getEmployee
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

/**
 * @author kimetsu - 02.06.2024 - 18:20
 */
@Service
class OrderService(
    private val repository: OrderRepository,
    private val employeeRepository: EmployeeRepository
) {

    fun getAll() =
        getEmployee()?.let { repository.findAllByResponsibleEmail(it) }?.toList()

    fun getByIdAndResponsible(id: Long) =
        getEmployee()?.let { repository.findByIdAndResponsibleEmail(id, it) } ?: throw NotFoundException()

    fun getByIdAndRespondent(id: Long) =
        getEmployee()?.let { repository.findByIdAndRespondentEmail(id, it) } ?: throw NotFoundException()


    fun create(req: OrderRequest) {
        val responsible = getEmployee()?.let { employeeRepository.findByEmail(it) } ?: throw NotFoundException()
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

    fun approve(id: Long): Order {
        val order = getEmployee()?.let { repository.findByResponsibleEmail(it) } ?: throw NotFoundException()
        order.apply { status = OrderStatus.SIGNED }
        return repository.save(order)
    }

}
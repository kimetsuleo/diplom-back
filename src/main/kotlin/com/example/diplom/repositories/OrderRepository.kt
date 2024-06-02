package com.example.diplom.repositories

import com.example.diplom.domain.Order
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author kimetsu - 02.06.2024 - 13:40
 */
interface OrderRepository: JpaRepository<Order, Long> {
    fun findAllByResponsibleEmail(email: String): List<Order>?

    fun findByResponsibleEmail(email: String): Order?

    fun findByIdAndResponsibleEmail(id:Long, email: String): Order?

    fun findByIdAndRespondentEmail(id:Long, email: String): Order?
}
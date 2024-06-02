package com.example.diplom.api.controllers

import com.example.diplom.api.controllers.OrderController.Companion.uri
import com.example.diplom.api.http.request.OrderRequest
import com.example.diplom.services.OrderService
import org.springframework.web.bind.annotation.*

/**
 * @author kimetsu - 02.06.2024 - 18:31
 */
@RestController
@RequestMapping(uri)
class OrderController(
    private val service: OrderService
) {

    @GetMapping
    fun getAll() =
        service.getAll()

    @GetMapping("{id}/responsible")
    fun getResponsibleOrder(@PathVariable id: Long) =
        service.getByIdAndResponsible(id)

    @GetMapping("{id}/responded")
    fun getRespondedOrder(@PathVariable id: Long) =
        service.getByIdAndRespondent(id)

    @PostMapping
    fun addOrder(@RequestBody req: OrderRequest) =
        service.create(req)

    @PutMapping("{id}")
    fun approve(@PathVariable id: Long) =
        service.approve(id)


    companion object {
        const val uri = "/api/v1/orders"
    }
}
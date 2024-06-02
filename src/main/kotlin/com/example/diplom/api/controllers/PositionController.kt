package com.example.diplom.api.controllers

import com.example.diplom.api.controllers.PositionController.Companion.uri
import com.example.diplom.services.PositionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author kimetsu - 02.06.2024 - 17:18
 */
@RestController
@RequestMapping(uri)
class PositionController(
    private val service: PositionService
) {

    @GetMapping
    fun getAll() =
        service.getAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long) =
        service.getById(id)


    companion object {
        const val uri = "/api/v1/positions"
    }
}
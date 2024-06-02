package com.example.diplom.api.controllers

import com.example.diplom.api.controllers.ContractController.Companion.uri
import com.example.diplom.api.http.request.ContractRequest
import com.example.diplom.services.ContractService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author kimetsu - 02.06.2024 - 17:36
 */
@RestController
@RequestMapping(uri)
class ContractController(
    private val service: ContractService
) {

    @GetMapping
    fun getAll() =
        service.getAll()

    @GetMapping("{id}")
    fun getById(@PathVariable id: Long) =
        service.getById(id)

    @PostMapping
    fun create(@RequestBody req: ContractRequest) =
        service.create(req)

    @PutMapping("{id}")
    fun update(@PathVariable id: Long, @RequestBody req: ContractRequest) =
        service.update(id, req)

    companion object {
        const val uri = "/api/v1/contracts"
    }
}
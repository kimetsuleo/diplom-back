package com.example.diplom.api.controllers

import com.example.diplom.api.controllers.EmployeeController.Companion.uri
import com.example.diplom.services.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author kimetsu - 03.06.2024 - 16:24
 */
@RestController
@RequestMapping(uri)
class EmployeeController(
    private val service: EmployeeService
) {

    @GetMapping("me")
    fun getMe() =
        service.getEmployee()

    @GetMapping
    fun getAll() =
        service.getAllEmployees()


    companion object {
        const val uri = "/api/v1/employees"
    }
}
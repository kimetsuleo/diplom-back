package com.example.diplom.api.controllers

import com.example.diplom.api.controllers.DayController.Companion.uri
import com.example.diplom.services.DayService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author kimetsu - 02.06.2024 - 19:49
 */
@RestController
@RequestMapping(uri)
class DayController(
    private val dayService: DayService
) {

    @GetMapping
    fun getDays() = dayService.getAll()

    companion object {
        const val uri = "/api/v1/days"
    }
}
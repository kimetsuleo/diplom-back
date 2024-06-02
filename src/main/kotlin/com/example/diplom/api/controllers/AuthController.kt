package com.example.diplom.api.controllers

import com.example.diplom.api.controllers.AuthController.Companion.uri
import com.example.diplom.api.http.request.AuthRequest
import com.example.diplom.api.http.request.RegisterRequest
import com.example.diplom.domain.Employee
import com.example.diplom.services.AuthService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author kimetsu - 02.06.2024 - 14:37
 */
@RestController
@RequestMapping(uri)
class AuthController(
    private val service: AuthService
) {

    @PostMapping("register")
    fun register(@RequestBody req: RegisterRequest) =
        service.register(req)

    @PostMapping("login")
    fun login(@RequestBody req: AuthRequest) =
        service.login(req)

    companion object {
        const val uri = "/api/auth"
    }
}
package com.example.diplom.services

import com.example.diplom.api.http.request.AuthRequest
import com.example.diplom.api.http.request.RegisterRequest
import com.example.diplom.api.http.response.TokenResponse
import com.example.diplom.domain.Employee
import com.example.diplom.exceptions.AllReadyExist
import com.example.diplom.repositories.EmployeeRepository
import com.example.diplom.repositories.PositionRepository
import com.example.diplom.security.TokenService
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * @author kimetsu - 02.06.2024 - 14:42
 */
@Service
class AuthService(
    private val repository: EmployeeRepository,
    private val positionRepository: PositionRepository,
    private val userDetailsService: CustomUserDetailsService,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val tokenService: TokenService,
    private val authenticationManager: AuthenticationManager
) {

    fun register(req: RegisterRequest): Employee {
        val position = positionRepository.findPositionById(req.position) ?: throw NotFoundException()

        if (repository.existsByEmail(req.email)
            || repository.existsByPhone(req.phone)
            || repository.existsByPassport(req.passport)
        ) {
            throw AllReadyExist()
        }

        return Employee(
            0,
            req.firstName,
            req.lastName,
            req.patronymic,
            position,
            req.phone,
            req.email,
            req.passport,
            req.birthdate,
            req.active,
            passwordEncoder.encode(req.password)
        ).run {
            repository.save(this)
        }
    }

    fun login(req: AuthRequest): TokenResponse? {
        val user = userDetailsService.loadUserByUsername(req.email)

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                req.email,
                req.password
            )
        )

        SecurityContextHolder.getContext().authentication = authentication

        val token = tokenService.generateToken(user.username)

        return TokenResponse(token)
    }
}
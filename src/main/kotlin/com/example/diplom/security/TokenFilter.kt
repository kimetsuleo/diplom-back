package com.example.diplom.security

import com.example.diplom.services.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * @author kimetsu - 02.06.2024 - 15:18
 */
@Component
class TokenFilter(
    private val service: TokenService,
    private val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = service.getTokenFromHeader(request)

        if (token != null && service.validateToken(token)) {
            val email = service.extractUsername(token)
            val userDetails = userDetailsService.loadUserByUsername(email)
            val authentication =
                UsernamePasswordAuthenticationToken(userDetails.username, null, userDetails.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

}
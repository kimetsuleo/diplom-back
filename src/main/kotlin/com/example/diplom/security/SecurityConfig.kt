package com.example.diplom.security

import com.example.diplom.repositories.EmployeeRepository
import com.example.diplom.repositories.PositionRepository
import com.example.diplom.services.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * @author kimetsu - 02.06.2024 - 13:54
 */
@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilter(
        http: HttpSecurity,
        repository: EmployeeRepository,
        positionRepository: PositionRepository,
        filter: TokenFilter
    ): SecurityFilterChain {

        http
            .csrf { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/api/auth/**").anonymous()
                    .anyRequest().authenticated()
            }
            .authenticationProvider(authenticationProvider(repository, positionRepository))
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder =
        BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager? =
        authenticationConfiguration.authenticationManager

    @Bean
    fun authenticationProvider(
        repository: EmployeeRepository,
        positionRepository: PositionRepository
    ): AuthenticationProvider =
        DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailsService(repository, positionRepository))
                it.setPasswordEncoder(passwordEncoder())
            }

    @Bean
    fun userDetailsService(
        repository: EmployeeRepository,
        positionRepository: PositionRepository
    ): UserDetailsService =
        CustomUserDetailsService(repository, positionRepository)
}
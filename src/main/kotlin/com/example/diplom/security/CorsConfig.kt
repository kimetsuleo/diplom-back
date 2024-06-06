package com.example.diplom.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * @author kimetsu - 03.06.2024 - 14:45
 */
@Configuration
class CorsConfig: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedMethods("*")
    }
}
package com.example.diplom.properties

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * @author kimetsu - 02.06.2024 - 13:44
 */
@ConfigurationProperties(prefix = "security")
data class SecurityProperties(
    val jwt: JwtProperties
) {

    data class JwtProperties(
        val expired: Long,
        val secret: String
    )
}

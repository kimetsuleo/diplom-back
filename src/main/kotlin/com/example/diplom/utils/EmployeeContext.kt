package com.example.diplom.utils

import org.springframework.security.core.context.SecurityContextHolder

/**
 * @author kimetsu - 02.06.2024 - 17:48
 */
fun getEmployee(): String? =
    SecurityContextHolder.getContext().authentication.principal.toString()



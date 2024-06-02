package com.example.diplom.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

/**
 * @author kimetsu - 02.06.2024 - 14:54
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException: RuntimeException("Not found")
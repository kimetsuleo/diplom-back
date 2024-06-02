package com.example.diplom.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * @author kimetsu - 02.06.2024 - 17:01
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
class AllReadyExist: RuntimeException("All ready exists")
package com.example.diplom.services

import com.example.diplom.repositories.DayRepository
import org.springframework.stereotype.Service

@Service
class DayService(
    private val repository: DayRepository
) {
    fun getAll() =
        repository.findAll()
}

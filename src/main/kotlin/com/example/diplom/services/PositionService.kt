package com.example.diplom.services

import com.example.diplom.repositories.PositionRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

/**
 * @author kimetsu - 02.06.2024 - 17:16
 */
@Service
class PositionService(
    private val repository: PositionRepository
) {
    fun getAll() =
        repository.findAll()

    fun getById(id: Long) =
        repository.findById(id).orElseThrow { throw NotFoundException() }
}
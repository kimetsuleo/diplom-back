package com.example.diplom.repositories;

import com.example.diplom.domain.Schedule
import com.example.diplom.domain.Shift
import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository : JpaRepository<Schedule, Long> {
    fun findByShift(shift: Shift): Schedule?
}
package com.example.diplom.repositories;

import com.example.diplom.domain.Shift
import org.springframework.data.jpa.repository.JpaRepository
import java.time.DayOfWeek

interface ShiftRepository : JpaRepository<Shift, Long> {
    fun findByDay(day: DayOfWeek): Shift?
}
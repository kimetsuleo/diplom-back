package com.example.diplom.repositories;

import com.example.diplom.domain.Schedule
import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository : JpaRepository<Schedule, Long> {
}
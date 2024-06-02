package com.example.diplom.repositories;

import com.example.diplom.domain.Day
import org.springframework.data.jpa.repository.JpaRepository

interface DayRepository : JpaRepository<Day, Long> {
}
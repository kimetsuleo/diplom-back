package com.example.diplom.repositories;

import com.example.diplom.domain.Shift
import org.springframework.data.jpa.repository.JpaRepository

interface ShiftRepository : JpaRepository<Shift, Long> {
}
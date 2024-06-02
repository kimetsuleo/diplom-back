package com.example.diplom.repositories

import com.example.diplom.domain.Position
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * @author kimetsu - 02.06.2024 - 13:41
 */
interface PositionRepository : JpaRepository<Position, Long> {

    fun findPositionById(id: Long): Position?

    @Query(
        value = """
        select p.* from positions p
        left join employees e on e.position_id = p.id
        where e.email = :email
    """, nativeQuery = true
    )
    fun findFromEmployeeByEmail(email: String): List<Position>?
}
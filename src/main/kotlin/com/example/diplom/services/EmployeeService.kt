package com.example.diplom.services

import com.example.diplom.api.http.response.EmployeeResponse
import com.example.diplom.api.http.response.ShiftResponse
import com.example.diplom.exceptions.NotFoundException
import com.example.diplom.repositories.EmployeeRepository
import com.example.diplom.repositories.ScheduleRepository
import com.example.diplom.repositories.ShiftRepository
import com.example.diplom.utils.EmployeeContext
import org.springframework.stereotype.Service
import java.time.LocalDate

/**
 * @author kimetsu - 03.06.2024 - 16:25
 */
@Service
class EmployeeService(
    private val repository: EmployeeRepository,
    private val shiftRepository: ShiftRepository,
    private val scheduleRepository: ScheduleRepository,
    private val context: EmployeeContext
) {

    fun getEmployee() =
        context.getEmployee()

    fun getAllEmployees(): List<EmployeeResponse> {
        val dayOfWeek = LocalDate.now().dayOfWeek
        val emps = repository.findAll()
        val shift = shiftRepository.findByDay(dayOfWeek) ?: throw IllegalArgumentException()
        val schedule  = scheduleRepository.findByShift(shift) ?: throw IllegalArgumentException()

        return emps.map {
            EmployeeResponse(
                schedule.employee.id!!,
                it.firstName,
                it.lastName,
                it.email,
                it.position,
                day = ShiftResponse(shift.day.name, shift.startAt, shift.endAt)
            )
        }
    }

    fun getEmployeeByEmail(email: String) =
        repository.findByEmail(email) ?: throw NotFoundException()

    fun getByPhone(phone: String) =
        repository.findByPhone(phone)

    fun getByFirstAndLastname(firstname: String, lastname: String) =
        repository.findAllByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstname, lastname)?.toList()

    fun getByFirstName(firstname: String) =
        repository.findAllByFirstNameContainingIgnoreCase(firstname)?.toList()

}
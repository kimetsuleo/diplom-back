package com.example.diplom

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class DiplomApplication

fun main(args: Array<String>) {
    runApplication<DiplomApplication>(*args)
}

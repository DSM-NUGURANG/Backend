package com.dohyeon5626.nugurang

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@ConfigurationPropertiesScan(basePackages = ["com.dohyeon5626.nugurang"])
@SpringBootApplication
class NugurangApplication

fun main(args: Array<String>) {
    runApplication<NugurangApplication>(*args)
}

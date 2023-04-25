package com.yourssu.igotIt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class IgotItApplication

fun main(args: Array<String>) {
	runApplication<IgotItApplication>(*args)
}

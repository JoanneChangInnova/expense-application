package com.mbTeam.expense

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@PropertySource("classpath:./activemq.properties")
@SpringBootApplication
class ExpenseApplication

fun main(args: Array<String>) {
	runApplication<ExpenseApplication>(*args)
}

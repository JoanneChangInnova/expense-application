package com.mbTeam.notification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.PropertySource

@PropertySource("classpath:./activemq.properties")
@SpringBootApplication
class NotificationApplication

fun main(args: Array<String>) {
	runApplication<NotificationApplication>(*args)
}

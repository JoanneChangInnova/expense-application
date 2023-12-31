package com.mbTeam.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class UserApplication: SpringBootServletInitializer()

fun main(args: Array<String>) {
	runApplication<UserApplication>(*args)
}

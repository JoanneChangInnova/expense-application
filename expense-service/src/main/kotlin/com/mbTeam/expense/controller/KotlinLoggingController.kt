package com.mbTeam.expense.controller

import com.mbTeam.expense.annotation.Slf4j
import com.mbTeam.expense.annotation.Slf4j.Companion.log
import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class KotlinLoggingController {

    /**
     * using KotlinLogging to implements logging function
     *
     * ref: https://segmentfault.com/a/1190000039649271
     * */
    private val logging = KotlinLogging.logger{}

    @GetMapping("/test/kotlin-logging")
    fun testLogWithKotlinLogging(): String {
        val wording = "test-kotlin-logging"
        logging.warn(wording)
        return wording
    }

    @GetMapping("/test/customer-annotation-logging")
    fun testLogWithCustomerAnnotationLogging(): String {
        val wording = "test-customer-annotation-logging"
        log.warn(wording)
        return wording
    }
}
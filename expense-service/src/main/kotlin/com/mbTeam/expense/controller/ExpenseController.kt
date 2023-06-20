package com.mbTeam.expense.controller

import org.springframework.jms.annotation.JmsListener
import org.springframework.web.bind.annotation.RestController

@RestController
class ExpenseController {
    /**
     * A consumer for testing
     */
//    @JmsListener(destination = "activemq-queue")
//    fun testConsumer(msg: String){
//        println("get message: $msg")
//    }
}
package com.mbTeam.notification.consumer

import com.mbTeam.notification.service.MailService
import com.mbTeam.notification.vo.MailObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.annotation.JmsListener
import org.springframework.web.bind.annotation.RestController

@RestController
class ExpenseController {

    @Autowired
    lateinit var mailService: MailService

    /**
     * A consumer for testing
     */
    @JmsListener(destination = "approver-queue")
    fun testConsumer(msg: String){
        try {
            println("JMS Listener received msg from approver-queue, msg: $msg")
            var mailObject= MailObject("","msg from approver-queue","msg consumed by JMS listener: $msg")
            mailService.mailSender(mailObject)
        }catch (e:Exception){
            println("Failed: ${e.message}")
        }
    }

}
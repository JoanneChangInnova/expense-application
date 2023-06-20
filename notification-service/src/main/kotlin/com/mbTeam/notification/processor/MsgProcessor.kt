package com.mbTeam.notification.processor

import com.mbTeam.notification.service.MailService
import com.mbTeam.notification.vo.MailObject
import com.mbTeam.notification.vo.QueueMsg
import org.springframework.beans.factory.annotation.Autowired

class MsgProcessor{
    @Autowired
    lateinit var mailService: MailService
    fun doProcess(qMsg: QueueMsg){
        println("Camel got msg from applyer-queue: ${qMsg.msg}")
        var mailObject= MailObject("","msg from applyer-queue", "msg consumed by camel: "+qMsg.msg)
        mailService.mailSender(mailObject)
    }
}
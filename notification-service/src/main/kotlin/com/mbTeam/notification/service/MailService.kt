package com.mbTeam.notification.service

import com.mbTeam.notification.vo.MailObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailService {

    @Autowired
    lateinit var javaMailSender: JavaMailSender

    fun mailSender(mailObject: MailObject){
        var message= SimpleMailMessage()
        message.setTo("Joanne.Chang@innovasolutions.com")
//        message.setSubject("Test for mail sending")
        message.setSubject(mailObject.subject)
        message.setText(mailObject.text)
        javaMailSender.send(message)
    }
}
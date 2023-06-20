package com.mbTeam.notification.component

import com.mbTeam.notification.processor.MsgProcessor
import com.mbTeam.notification.vo.QueueMsg
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.model.dataformat.JsonLibrary
import org.springframework.stereotype.Component

@Component
class CamelRouter: RouteBuilder() {
    override fun configure() {
        from("activemq:queue:applyer-queue")
            .unmarshal().json(JsonLibrary.Jackson, QueueMsg::class.java)
            .bean(MsgProcessor::class.java, "doProcess")
    }
}
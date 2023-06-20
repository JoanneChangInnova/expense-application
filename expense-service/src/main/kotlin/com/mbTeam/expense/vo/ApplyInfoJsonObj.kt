package com.mbTeam.expense.vo

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApplyInfoJsonObj(
    var applier: Long,
    @JsonFormat(pattern= "yyyy-MM-dd@HH:mm:ss.SSSZ")
    var applyDate: Date
){
    var approver: Long?=null
    @JsonFormat(pattern= "yyyy-MM-dd@HH:mm:ss.SSSZ")
    var approveDate: Date?= null
}

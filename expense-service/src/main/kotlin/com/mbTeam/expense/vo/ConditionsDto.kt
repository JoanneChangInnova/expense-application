package com.mbTeam.expense.vo


data class ConditionsDto(
    var userId: Long,
    var roleId: Long?=null,
    var type: Int?=null,
    var status: Int?=null,
    var startTime: String?=null,
    var endTime: String?=null
    )

package com.mbTeam.expense.vo

import java.util.*

data class ApplyInfo(
    var userId: Long,
    var type: Int,
    var amount: Int,
    var reason: String,
    var startTime: Date,
    var endTime: Date,
    var adminReason: String?
)

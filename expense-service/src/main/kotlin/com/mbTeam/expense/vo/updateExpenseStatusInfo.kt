package com.mbTeam.expense.vo

data class UpdateExpenseStatusInfo(
    var approverId: Long,
    var status: Int,
    var adminReason: String? =null
)

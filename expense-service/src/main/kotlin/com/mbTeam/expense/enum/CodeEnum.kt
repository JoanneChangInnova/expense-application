package com.mbTeam.expense.enum

enum class CodeEnum(val code: Int, val category: String) {
    //Status
    Submitted(1, "status"),
    Rejected(2, "status"),
    Approved(3, "status"),
    Canceled(4, "status"),
    //Type
    Traveling(5, "type"),
    GroupMeal(6, "type")
}
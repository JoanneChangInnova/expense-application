package com.mbTeam.expense.vo

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.mbTeam.expense.entity.Expense
import kotlinx.serialization.Serializable
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ExpenseVo(
    var totalElements: Long?,
    var totalPages: Int?,
    var expenses: List<Expense>?
)
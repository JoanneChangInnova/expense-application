package com.mbTeam.expense.vo

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable

@Serializable
data class QueueMsg(@JsonProperty("msg") var msg: String){

}
package com.mbTeam.expense.entity

import javax.persistence.*

@Entity
@Table(name="code")
data class Code(
    var codeName: String,
    var codeCategory: String
){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?=0
}

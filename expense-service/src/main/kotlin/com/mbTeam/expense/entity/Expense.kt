package com.mbTeam.expense.entity

import com.fasterxml.jackson.annotation.JsonFormat
import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import java.util.*
import javax.persistence.*

@Entity
@Table(name="expense")
@TypeDefs(
    TypeDef(name = "json", typeClass = JsonStringType::class),
    TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
)
data class Expense(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Long=0,
    var userId: Long,
    var type: Int,
    var status: Int,
    var amount: Int,
    var reason: String,
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    var applyInfo: String,
    @JsonFormat(pattern= "yyyy-MM-dd@HH:mm:ss.SSSZ")
    var createTime: Date,
    @JsonFormat(pattern= "yyyy-MM-dd@HH:mm:ss.SSSZ")
    var startTime: Date,
    @JsonFormat(pattern= "yyyy-MM-dd@HH:mm:ss.SSSZ")
    var endTime: Date,
    var adminReason: String?
    )
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long?=0
}
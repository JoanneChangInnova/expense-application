package com.mbTeam.user.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Users (
    var name: String,
    var account: String,
    var password: String,
    var role_id: Long
        ){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?=0
}
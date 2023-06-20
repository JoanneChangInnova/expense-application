package com.mbTeam.user.vo

data class NewUserInfo(
    var name: String,
    var account: String,
    var password: String,
    var roleId: Long
)

package com.mbTeam.user.vo

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class LoginStatusVo(
    var code: Int,
    var msg: String,
    var data: UserInfoVo?
)
{
    constructor() : this(444, "Init failed", null)
}

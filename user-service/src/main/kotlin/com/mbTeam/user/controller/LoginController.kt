package com.mbTeam.user.controller

import com.mbTeam.user.service.LoginService
import com.mbTeam.user.vo.LoginInfo
import com.mbTeam.user.vo.LoginStatusVo
import com.mbTeam.user.vo.UserInfoVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class LoginController {

    @Autowired
    lateinit var loginService: LoginService

    @PostMapping("/login")
    fun loginCheck(@RequestBody info: LoginInfo): LoginStatusVo {
        var result = LoginStatusVo()
        var userInfoVo = loginService.checkLogin(info)
        if (userInfoVo != null) {
            result.code = 200
            result.msg = "Authenticated User"
        } else {
            result.code = 400
            result.msg = "Unauthenticated User"
        }
        result.data = userInfoVo
        return result
    }
}
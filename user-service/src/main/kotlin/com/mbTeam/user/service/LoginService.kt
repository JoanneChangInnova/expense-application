package com.mbTeam.user.service

import com.mbTeam.user.repository.UsersRepository
import com.mbTeam.user.utils.EncryptionUtils
import com.mbTeam.user.vo.LoginInfo
import com.mbTeam.user.vo.UserInfoVo
import org.apache.catalina.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LoginService {

    @Autowired
    lateinit var usersRepository: UsersRepository

    fun checkLogin(loginInfo: LoginInfo): UserInfoVo?{
        var result: UserInfoVo?=null
        var pwd: String = EncryptionUtils.sha256(loginInfo.password)
        var users= usersRepository.findByAccount(loginInfo.account)
        if(pwd == users?.password){
            result = UserInfoVo(users?.id, users.name, users.account, users.role_id)
        }
        return result
    }
}
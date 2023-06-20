package com.mbTeam.user.service

import com.mbTeam.user.entity.Users
import com.mbTeam.user.repository.UsersRepository
import com.mbTeam.user.utils.EncryptionUtils
import com.mbTeam.user.vo.NewUserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var usersRepository: UsersRepository

    fun createNewUser(newUserInfo: NewUserInfo){

        var newUser= Users(
            newUserInfo.name,
            newUserInfo.account,
            EncryptionUtils.sha256(newUserInfo.password),
            newUserInfo.roleId
        )
        usersRepository.save(newUser)
    }

}
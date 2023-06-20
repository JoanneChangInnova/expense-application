package com.mbTeam.user.controller

import com.mbTeam.user.service.UserService
import com.mbTeam.user.vo.NewUserInfo
import com.mbTeam.user.vo.ResponseVo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/user")
    fun getAllUsers(): ResponseVo<Any> {
        return ResponseVo(200, " user", null)
    }

    @PostMapping("/apply")
    fun createNewUser(@RequestBody newUserInfo: NewUserInfo): ResponseVo<Any> {
        return try {
            checkField(newUserInfo)
            userService.createNewUser(newUserInfo)
            ResponseVo(200, "Create new user successfully", null)
        }catch (e: Exception){
            println("Failed to create new user, msg: ${e.message}")
            ResponseVo(400, "Failed to create new user", null)
        }
    }

    fun checkField(t:Any){
        val isAnyFieldNull = listOf(t).any { it==null }
        if(isAnyFieldNull){
            println("Parameters needed could not be null: $isAnyFieldNull")
            throw Exception("Parameters needed could not be null: $isAnyFieldNull")
        }
    }
}
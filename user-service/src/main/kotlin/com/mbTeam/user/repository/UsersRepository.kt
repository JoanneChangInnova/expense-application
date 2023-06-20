package com.mbTeam.user.repository

import com.mbTeam.user.entity.Users
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository: CrudRepository<Users, Long> {
    fun findByAccount(account: String): Users?

}
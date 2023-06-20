package com.mbTeam.expense.repository

import com.mbTeam.expense.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsersRepository: JpaRepository<Users, Long> {
    fun findByAccount(account: String): Users?

}
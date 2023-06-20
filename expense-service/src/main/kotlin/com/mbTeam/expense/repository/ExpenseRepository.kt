package com.mbTeam.expense.repository

import com.mbTeam.expense.entity.Expense
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ExpenseRepository : JpaRepository<Expense, Long>, PagingAndSortingRepository<Expense, Long>,JpaSpecificationExecutor<Expense>{

}
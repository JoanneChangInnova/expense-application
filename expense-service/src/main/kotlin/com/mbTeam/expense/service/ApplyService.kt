package com.mbTeam.expense.service

import au.com.console.jpaspecificationdsl.*
import com.google.gson.Gson
import com.mbTeam.expense.entity.Expense
import com.mbTeam.expense.enum.CodeEnum
import com.mbTeam.expense.repository.ExpenseRepository
import com.mbTeam.expense.repository.UsersRepository
import com.mbTeam.expense.utils.DateUtils
import com.mbTeam.expense.vo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.domain.Specification
import org.springframework.jms.core.JmsMessagingTemplate
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class ApplyService {

    @Autowired
    lateinit var expenseRepository: ExpenseRepository

    @Autowired
    lateinit var usersRepository: UsersRepository

    @Autowired
    private val jmsMessagingTemplate = JmsMessagingTemplate()

    private val gson: Gson = Gson()

    /*
    * Retrieves a list of expenses based on the provided query conditions,
    * return page number, and page size.
    * query conditions differs by roleId -> 1:admin 2:user
    * */
    fun getAllApplyList(conditionsDto: ConditionsDto, page: Int, pageSize: Int): ExpenseVo {
        val pageRequest=PageRequest.of(page,pageSize, Sort.by("id"))

        var users = usersRepository.getById(conditionsDto.userId)

        conditionsDto.roleId = users.role_id

        var expenseList = expenseRepository.findAll(genConditionSpecification(conditionsDto), pageRequest)
        return ExpenseVo(
            expenseList.totalElements,
            expenseList.totalPages,
            expenseList.content
        )
    }

    /*
    * Generates a specification object for querying expenses based on
    * the user's roleId, type, status and createTime
    * */
    fun genConditionSpecification(conditionsDto: ConditionsDto?): Specification<Expense>? = conditionsDto?.let {
        var specList: ArrayList<Specification<Expense>> = arrayListOf()

        if (conditionsDto != null && conditionsDto.roleId == 2L) {
            specList.add(Expense::userId.equal(it.userId))
        }
        if (it.type != null) {
            specList.add(Expense::type.equal(it.type))
        }
        if (it.status != null) {
            specList.add(Expense::status.equal(it.status))
        }
        if (it.startTime != null && it.endTime != null) {
            specList.add(
                Expense::createTime.between(
                    DateUtils.dateStrToIsoDate(it.startTime),
                    DateUtils.dateStrToIsoDate(it.endTime)
                )
            )
        }
        return and(specList)
    }

    fun createExpenseApplication(applyInfo: ApplyInfo) {
        var expense = Expense(
            applyInfo.userId,
            applyInfo.type,
            CodeEnum.Submitted.code,
            applyInfo.amount,
            applyInfo.reason,
            genApplyInfoJson(applyInfo.userId),
            Date(),
            applyInfo.startTime,
            applyInfo.endTime,
            applyInfo?.adminReason
        )
        expenseRepository.save(expense)
        jmsMessagingTemplate.convertAndSend(
            "approver-queue",
            "userID: ${applyInfo.userId}, has requested an expense application"
        )
    }

    private fun genApplyInfoJson(userId: Long): String {
        var jsonObj = ApplyInfoJsonObj(userId, Date())
        return gson.toJson(jsonObj)
    }

    fun getExpenseById(id: Long) = expenseRepository.findById(id)

}
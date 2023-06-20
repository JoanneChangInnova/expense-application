package com.mbTeam.expense.service

import com.google.gson.Gson
import com.mbTeam.expense.enum.CodeEnum
import com.mbTeam.expense.repository.ExpenseRepository
import com.mbTeam.expense.vo.ApplyInfoJsonObj
import com.mbTeam.expense.vo.QueueMsg
import com.mbTeam.expense.vo.UpdateExpenseStatusInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.core.JmsMessagingTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApproveService {

    @Autowired
    lateinit var expenseRepository: ExpenseRepository

    @Autowired
    private val jmsMessagingTemplate= JmsMessagingTemplate()

    private val gson: Gson = Gson()

    fun updateStatus(updateExpenseStatusInfo: UpdateExpenseStatusInfo, expenseId: Long){
        if(updateExpenseStatusInfo.status==null){
            throw Exception("Status cannot be null")
        }
        if(!isValidStatus(updateExpenseStatusInfo.status)){
            throw Exception("Status code is not valid")
        }
        var expense = expenseRepository.findById(expenseId)
        if(expense.isPresent){
            expense.get().status=updateExpenseStatusInfo.status
            if(updateExpenseStatusInfo.adminReason != null) expense.get().adminReason=updateExpenseStatusInfo.adminReason
            var newJsonObject = updateJsonObject(updateExpenseStatusInfo.approverId, expense.get().applyInfo)

            expense.get().applyInfo=newJsonObject
            expenseRepository.save(expense.get())
        }
        if(updateExpenseStatusInfo.status == CodeEnum.Approved.code){
            var qMsg= QueueMsg("Expense ID: $expenseId, has been approved")
            jmsMessagingTemplate.convertAndSend("applyer-queue", gson.toJson(qMsg))
        }
    }

    private fun isValidStatus(status: Int): Boolean{
        for(enum: CodeEnum in CodeEnum.values()){
            if(enum.code==status){
                return enum.category=="status"
            }
        }
        return false
    }

    private fun updateJsonObject(approverId: Long, jsonObj: String): String{
        var info = gson.fromJson(jsonObj, ApplyInfoJsonObj::class.java)
        info.approver=approverId
        info.approveDate= Date()
        return gson.toJson(info)
    }
}
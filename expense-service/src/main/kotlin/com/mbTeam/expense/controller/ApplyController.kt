package com.mbTeam.expense.controller

import com.google.gson.Gson
import com.mbTeam.expense.entity.Expense
import com.mbTeam.expense.enum.CodeEnum
import com.mbTeam.expense.service.ApplyService
import com.mbTeam.expense.vo.*

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.core.JmsMessagingTemplate
import org.springframework.web.bind.annotation.*



@RequestMapping
@RestController
@CrossOrigin
@Api(value = "Apply", tags = ["Expense Service"])
class ApplyController {
    @Autowired
    lateinit var applyService: ApplyService

    private val gson: Gson = Gson()

    /**
     * for sender testing
     */
//    @Value("\${activemq.queueName}")
//    private lateinit var qName:String
//
//    @Autowired
//    private val jmsMessagingTemplate= JmsMessagingTemplate()
//
//    @GetMapping("/send/{msg}")
//    fun send(@PathVariable msg:String){
//        jmsMessagingTemplate.convertAndSend(qName, msg)
//    }

    @ApiOperation("List Expense Data")
    @GetMapping("/apply")
    fun getApplyList(@RequestParam(required = false) userId: Long,
                     @RequestParam(required = false) type: Int?,
                     @RequestParam(required = false) status: Int?,
                     @RequestParam(required = false) startTime: String?,
                     @RequestParam(required = false) endTime: String?,
                     @RequestParam(required = false, defaultValue = "0")page: Int,
                     @RequestParam(required = false, defaultValue = "10")pageSize: Int): ResponseVo<ExpenseVo> {
        try {
            var conditionsDtoto = ConditionsDto(userId, null, type, status, startTime, endTime)

            var list = applyService.getAllApplyList(conditionsDtoto, page, pageSize)
            return ResponseVo(200, "Get list successfully", list)
        } catch (e: Exception) {
            println("Failed to get expense list")
            return ResponseVo(400, "Failed to get expense list", null)
        }
    }

    @ApiOperation("Get Expense Data By Id")
    @GetMapping("/apply/{id}")
    fun getExpenseInfoById(@PathVariable("id") expenseId: Long): ResponseVo<Expense> {
        try {
            var result = applyService.getExpenseById(expenseId)
            return ResponseVo(200, "Apply successfully", result.get())
        } catch (e: Exception) {
            println("Failed to get expense info, id: $expenseId")
            return ResponseVo(400, "Failed to get expense info", null)
        }
    }

    @ApiOperation("Add Expense Data")
    @PostMapping("/apply")
    fun createApplication(@RequestBody applyInfo: ApplyInfo): ResponseVo<Any> {
        try {
            // if the status contains traveling or group meal, column amount should be validated
            if (CodeEnum.Traveling.code.equals(applyInfo.type) && applyInfo.amount > 5000) {
                return ResponseVo(400, "Failed to apply the traveling expense", null)
            }
            if (CodeEnum.GroupMeal.code.equals(applyInfo.type) && applyInfo.amount > 2000) {
                return ResponseVo(400, "Failed to apply the group meal expense", null)
            }
            applyService.createExpenseApplication(applyInfo)
            return ResponseVo(200, "Apply successfully", null)
        } catch (e: Exception) {
            println("Failed to apply expense: ${gson.toJson(applyInfo)}")
            return ResponseVo(400, "Failed to apply expense", null)
        }
    }
}
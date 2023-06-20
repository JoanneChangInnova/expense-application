package com.mbTeam.expense.controller

import com.mbTeam.expense.enum.CodeEnum
import com.mbTeam.expense.service.ApproveService
import com.mbTeam.expense.vo.ResponseVo
import com.mbTeam.expense.vo.UpdateExpenseStatusInfo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
@Api(value = "Approve", tags = ["Expense Service"])
class ApproveController {

    @Autowired
    lateinit var approveService: ApproveService

    @ApiOperation("Update Expense Status")
    @PatchMapping("/apply/{id}")
    fun updateExpenseStatus(@RequestBody updateExpenseStatusInfo: UpdateExpenseStatusInfo, @PathVariable("id") expenseId: Long): ResponseVo<Any>{
        try {
            // if status is Reject, column adminReason must have value
            if (CodeEnum.Rejected.code.equals(updateExpenseStatusInfo.status) &&
                StringUtils.isBlank(updateExpenseStatusInfo.adminReason)) {
                return  ResponseVo(400, "Failed to update for reject status, column adminReason must have value", null)
            }
            approveService.updateStatus(updateExpenseStatusInfo, expenseId)
            return ResponseVo(200, "Update successfully", null)
        }catch (e: Exception){
            println("Failed to update status, expense id: $expenseId , update status: ${updateExpenseStatusInfo.status} , message: "+e.message)
            return  ResponseVo(400, "Failed to update", null)
        }
    }
}
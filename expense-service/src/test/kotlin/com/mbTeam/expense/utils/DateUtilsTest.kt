package com.mbTeam.expense.utils

import org.assertj.core.util.DateUtil
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class DateUtilsTest {
    private val dateUtils: DateUtils = DateUtils

    @Test
    fun testDateUtils(){
        val dateStr1="2021-12-09"
        val dateStr2="2021-12-09T10:20:32.234Z"
        println(dateUtils.dateStrToIsoDate(dateStr1))
        println(dateUtils.dateStrToIsoDateTime(dateStr2))
    }
}
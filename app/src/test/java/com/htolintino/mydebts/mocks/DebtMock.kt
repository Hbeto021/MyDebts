package com.htolintino.mydebts.mocks

import com.htolintino.mydebts.commons.domain.entity.Debt

object DebtMock {

    val description = "description"
    val value = "value"
    val month = "may"
    val dueDate = ""
    val year = "2020"

    val debt = Debt(0,
        description,
        value,
        month,
        year,
        dueDate
    )
}
package com.htolintino.mydebts.features.manageDebt.dataModel

import com.htolintino.mydebts.commons.domain.entity.Debt

interface IManageDebtDataModel {

    fun isValidDebt(description: String, value: String, month: String): Boolean
    fun isDebtNotNull(debt: Debt?): Boolean
}
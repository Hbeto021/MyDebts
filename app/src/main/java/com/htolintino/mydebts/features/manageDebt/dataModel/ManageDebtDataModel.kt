package com.htolintino.mydebts.features.manageDebt.dataModel

import com.htolintino.mydebts.commons.domain.entity.Debt

class ManageDebtDataModel : IManageDebtDataModel {

    override fun isValidDebt(description: String, value: String, month: String): Boolean {
        return description.isNotEmpty() && value.isNotEmpty() && month.isNotEmpty()
    }

    override fun isDebtNotNull(debt: Debt?): Boolean {
        debt?.let {
            return true
        }
        return false
    }
}
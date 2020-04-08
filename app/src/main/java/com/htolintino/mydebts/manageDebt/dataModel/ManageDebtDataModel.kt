package com.htolintino.mydebts.manageDebt.dataModel

class ManageDebtDataModel: IManageDebtDataModel {

    override fun isValidDebt(description: String, value: String, month: String): Boolean {
        return description.isNotEmpty() && value.isNotEmpty() && month.isNotEmpty()
    }

}
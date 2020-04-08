package com.htolintino.mydebts.manageDebt.dataModel

interface IManageDebtDataModel {

    fun isValidDebt(description: String, value: String, month: String): Boolean
}
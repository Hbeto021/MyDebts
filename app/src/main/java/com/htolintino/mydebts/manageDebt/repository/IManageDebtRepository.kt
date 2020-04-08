package com.htolintino.mydebts.manageDebt.repository

import com.htolintino.mydebts.commons.domain.entity.Debt


interface IManageDebtRepository {
    fun insertDebt(description: String, value: String, month: String)
    fun updateDebt(debt: Debt)
}
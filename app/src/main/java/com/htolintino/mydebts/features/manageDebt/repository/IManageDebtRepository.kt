package com.htolintino.mydebts.features.manageDebt.repository

import com.htolintino.mydebts.commons.domain.entity.Debt


interface IManageDebtRepository {
    suspend fun insertDebt(description: String, value: String, month: String, year: String,
                           dueDate: String)
    suspend fun updateDebt(debt: Debt)
}
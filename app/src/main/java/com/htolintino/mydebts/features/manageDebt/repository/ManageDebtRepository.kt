package com.htolintino.mydebts.features.manageDebt.repository

import com.htolintino.mydebts.dao.DebtDao
import com.htolintino.mydebts.commons.domain.entity.Debt


class ManageDebtRepository(private val debtDao: DebtDao): IManageDebtRepository {

    override suspend fun insertDebt(description: String, value: String, month: String,
                                    year: String, dueDate: String) {
        debtDao.insertDebt(
            Debt(description = description,
            value = value,
            month = month,
            year = year,
            dueDate = dueDate))
    }

    override suspend fun updateDebt(debt: Debt) {
        debtDao.updateDebt(debt)
    }

}
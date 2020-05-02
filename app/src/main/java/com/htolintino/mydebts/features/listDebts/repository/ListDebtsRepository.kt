package com.htolintino.mydebts.features.listDebts.repository

import androidx.lifecycle.LiveData
import com.htolintino.mydebts.dao.DebtDao
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.commons.helpers.ValuesFormatter
import java.text.DateFormatSymbols
import java.util.*

class ListDebtsRepository(private val debtDao: DebtDao) : IListDebtsRepository {

    override fun fetchCurrentMonthListDebts(): LiveData<List<Debt>> {
        //build the request
        val calendar = Calendar.getInstance()
        return debtDao.selectCurrentMonthListDebts(
            ValuesFormatter.formatMonth(calendar.get(Calendar.MONTH))
        )
    }

    override suspend fun fetchListDebtsPerMonth(month: String): List<Debt> {
        return debtDao.selectListDebtPerMonth(month)
    }

    override suspend fun deleteDebt(debt: Debt) {
        debtDao.deleteDebt(debt)
    }

}
package com.htolintino.mydebts.features.listDebts.repository

import androidx.lifecycle.LiveData
import com.htolintino.mydebts.commons.domain.entity.Debt

interface IListDebtsRepository {

    fun fetchCurrentMonthListDebts(): LiveData<List<Debt>>
    suspend fun fetchListDebtsPerMonth(month: String): List<Debt>
    suspend fun deleteDebt(debt: Debt)
}
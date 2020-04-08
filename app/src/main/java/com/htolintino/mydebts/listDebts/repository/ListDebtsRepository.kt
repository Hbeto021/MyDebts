package com.htolintino.mydebts.listDebts.repository

import androidx.lifecycle.LiveData
import com.htolintino.mydebts.dao.DebtDao
import com.htolintino.mydebts.commons.domain.entity.Debt

class ListDebtsRepository(private val debtDao: DebtDao) : IListDebtsRepository {

    override fun fetchListDebts(): LiveData<List<Debt>> {
        return debtDao.selectListDebts()
    }

}
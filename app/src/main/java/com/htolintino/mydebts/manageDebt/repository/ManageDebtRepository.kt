package com.htolintino.mydebts.manageDebt.repository

import android.os.AsyncTask
import com.htolintino.mydebts.dao.DebtDao
import com.htolintino.mydebts.commons.domain.entity.Debt


class ManageDebtRepository(private val debtDao: DebtDao): IManageDebtRepository {

    override fun insertDebt(description: String, value: String, month: String) {
        InsertDebt(debtDao).execute(Debt(
            description = description,
            value = value,
            month = month))
    }

    override fun updateDebt(debt: Debt) {
        UpdateDebt(debtDao).execute(debt)
    }

    //Room does not permit long operations in UI Thread (main thread)
    class InsertDebt(private val debtDao: DebtDao): AsyncTask<Debt, Void, Void>() {
        override fun doInBackground(vararg params: Debt): Void? {
            debtDao.insertDebt(params[0])
            return null
        }
    }

    class UpdateDebt(private val debtDao: DebtDao): AsyncTask<Debt, Void, Void>() {
        override fun doInBackground(vararg params: Debt): Void? {
            debtDao.updateDebt(params[0])
            return null
        }
    }

}
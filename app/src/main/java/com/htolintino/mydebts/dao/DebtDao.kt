package com.htolintino.mydebts.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.htolintino.mydebts.commons.domain.entity.Debt

@Dao
abstract class DebtDao {

    @Query("SELECT * FROM debt WHERE month LIKE :month ORDER BY id DESC")
    abstract fun selectCurrentMonthListDebts(month: String): LiveData<List<Debt>>

    @Query("SELECT * FROM debt WHERE month LIKE :month ORDER BY id DESC")
    abstract suspend fun selectListDebtPerMonth(month: String): List<Debt>

    @Insert
    abstract suspend fun insertDebt(debt: Debt)

    @Update
    abstract suspend fun updateDebt(debt: Debt)

    @Delete
    abstract suspend fun deleteDebt(debt: Debt)

}
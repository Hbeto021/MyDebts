package com.htolintino.mydebts.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.htolintino.mydebts.commons.domain.entity.Debt

@Dao
interface DebtDao {

    @Query("SELECT * FROM debt ORDER BY id DESC")
    fun selectListDebts(): LiveData<List<Debt>>

    @Insert
    fun insertDebt(debt: Debt)

    @Update
    fun updateDebt(debt: Debt)
}
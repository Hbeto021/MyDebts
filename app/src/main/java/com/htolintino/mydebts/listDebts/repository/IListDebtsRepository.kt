package com.htolintino.mydebts.listDebts.repository

import androidx.lifecycle.LiveData
import com.htolintino.mydebts.commons.domain.entity.Debt

interface IListDebtsRepository {
    fun fetchListDebts(): LiveData<List<Debt>>
}
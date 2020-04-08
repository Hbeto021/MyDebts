package com.htolintino.mydebts.listDebts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.listDebts.repository.IListDebtsRepository

class ListDebtsViewModel(private val listDebtsRepository: IListDebtsRepository): ViewModel() {

    fun fetchListDebts(): LiveData<List<Debt>> {
        return listDebtsRepository.fetchListDebts()
    }

}
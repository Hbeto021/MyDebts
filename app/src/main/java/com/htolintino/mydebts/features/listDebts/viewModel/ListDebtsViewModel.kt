package com.htolintino.mydebts.features.listDebts.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.commons.helpers.ValuesFormatter
import com.htolintino.mydebts.features.listDebts.repository.IListDebtsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class ListDebtsViewModel(private val listDebtsRepository: IListDebtsRepository) : ViewModel() {

    private val listDebts: MutableLiveData<List<Debt>> by lazy {
        MutableLiveData<List<Debt>>()
    }

    private val debtsValueInMonth: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val selectedMonth: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private var month = ""

    fun fetchListDebtPerMonth(selectedMonth: String) {
        viewModelScope.launch {
            val debts = withContext(Dispatchers.Default) {
                listDebtsRepository.fetchListDebtsPerMonth(selectedMonth)
            }

            month = selectedMonth
            listDebts.value = debts
        }
    }

    fun fetchInfoAboutSelectedMonth(listDebts: List<Debt>) {
        showInfoAboutMonth(listDebts, month)
    }

    fun fetchInfoAboutCurrentMonth(listDebts: List<Debt>) {
        showInfoAboutMonth(listDebts,
            ValuesFormatter.formatMonth(Calendar.getInstance().get(Calendar.MONTH)))
    }

    private fun showInfoAboutMonth(listDebts: List<Debt>, selectedMonth: String) {
        //month selected
        this.selectedMonth.value = selectedMonth

        //current debt value in selected month
        var debtsValueInMonth = 0.0
        listDebts.forEach { debt ->
            debtsValueInMonth += ValuesFormatter.removeMonetaryMask(debt.value).toDouble()
        }

        this.debtsValueInMonth.value =
            ValuesFormatter.formatToMonetaryValue(debtsValueInMonth.toString())
    }

    fun deleteDebt(debt: Debt) {
        viewModelScope.launch {
            listDebtsRepository.deleteDebt(debt)
        }
    }

    /**
     * Observers by view
     */
    fun observeDebtsValueInMonth(): LiveData<String> {
        return debtsValueInMonth
    }

    fun observeCurrentListDebts(): LiveData<List<Debt>> {
        //observers the database
        return listDebtsRepository.fetchCurrentMonthListDebts()
    }

    fun observeListDebtPerMonth(): LiveData<List<Debt>> {
        return listDebts
    }

    fun observeSelectedMonth(): LiveData<String> {
        return selectedMonth
    }
}
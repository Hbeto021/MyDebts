package com.htolintino.mydebts.manageDebt.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.manageDebt.dataModel.IManageDebtDataModel
import com.htolintino.mydebts.manageDebt.repository.IManageDebtRepository

class ManageDebtViewModel(private val manageDebtRepository: IManageDebtRepository,
                          private val menageDebtDataModel: IManageDebtDataModel): ViewModel() {

    private val errorMessageObservable: MutableLiveData<Void> by lazy {
        MutableLiveData<Void>()
    }

    private val debtObservable: MutableLiveData<Debt> by lazy {
        MutableLiveData<Debt>()
    }

    private val closeManageView: MutableLiveData<Void> by lazy {
        MutableLiveData<Void>()
    }

    private lateinit var debt: Debt

    fun addDebt(description: String, value: String, month: String) {
        if(menageDebtDataModel.isValidDebt(description, value, month)) {
            manageDebtRepository.insertDebt(description, value, month)
            closeManageView.value = null

        } else {
            errorMessageObservable.value = null
        }

    }

    fun editDebt(description: String, value: String, month: String) {
        if(menageDebtDataModel.isValidDebt(description, value, month)) {
            debt.description = description
            debt.value = value
            debt.month = month
            manageDebtRepository.updateDebt(debt)
            closeManageView.value = null

        } else {
            errorMessageObservable.value = null
        }
    }

    fun getErrorMessage(): MutableLiveData<Void> {
        return errorMessageObservable
    }

    fun setDebt(debt: Debt?) {
        debt?.let {
            debtObservable.value = debt
            this.debt = debt
        }
    }

    fun getDebt(): MutableLiveData<Debt> {
        return debtObservable
    }

    fun closeManageView(): MutableLiveData<Void> {
        return closeManageView
    }

}
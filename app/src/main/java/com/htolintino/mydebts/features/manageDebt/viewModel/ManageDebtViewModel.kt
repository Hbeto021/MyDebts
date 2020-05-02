package com.htolintino.mydebts.features.manageDebt.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.htolintino.mydebts.commons.domain.entity.Debt
import com.htolintino.mydebts.features.manageDebt.dataModel.IManageDebtDataModel
import com.htolintino.mydebts.features.manageDebt.repository.IManageDebtRepository
import kotlinx.coroutines.launch
import java.util.*

class ManageDebtViewModel(
    private val manageDebtRepository: IManageDebtRepository,
    private val menageDebtDataModel: IManageDebtDataModel
) : ViewModel() {

    private val errorMessage: MutableLiveData<Void> by lazy {
        MutableLiveData<Void>()
    }

    private val debtLiveData: MutableLiveData<Debt> by lazy {
        MutableLiveData<Debt>()
    }

    private val closeManageView: MutableLiveData<Void> by lazy {
        MutableLiveData<Void>()
    }

    fun addDebt(description: String, value: String, month: String, dueDate: String) {
        if (menageDebtDataModel.isValidDebt(description, value, month)) {

            viewModelScope.launch {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                manageDebtRepository.insertDebt(
                    description,
                    value,
                    month,
                    year.toString(),
                    dueDate
                )
                closeManageView.value = null
            }

        } else {
            errorMessage.value = null
        }

    }

    fun editDebt(description: String, value: String, month: String, dueDate: String) {
        if (menageDebtDataModel.isValidDebt(description, value, month)) {

            viewModelScope.launch {
                debtLiveData.value?.let { debt ->
                    debt.description = description
                    debt.value = value
                    debt.month = month
                    debt.dueDate = dueDate
                    manageDebtRepository.updateDebt(debt)
                    closeManageView.value = null
                }
            }

        } else {
            errorMessage.value = null
        }
    }

    fun setDebt(debt: Debt?) {
        if (menageDebtDataModel.isDebtNotNull(debt)) {
            debtLiveData.value = debt
        }
    }

    /**
     * Observers by view
     */
    fun observeErrorMessage(): MutableLiveData<Void> {
        return errorMessage
    }


    fun observeDebt(): MutableLiveData<Debt> {
        return debtLiveData
    }

    fun closeManageView(): MutableLiveData<Void> {
        return closeManageView
    }

}
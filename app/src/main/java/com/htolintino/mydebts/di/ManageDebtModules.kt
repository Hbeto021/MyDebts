package com.htolintino.mydebts.di

import com.htolintino.mydebts.manageDebt.dataModel.IManageDebtDataModel
import com.htolintino.mydebts.manageDebt.dataModel.ManageDebtDataModel
import com.htolintino.mydebts.manageDebt.repository.IManageDebtRepository
import com.htolintino.mydebts.manageDebt.repository.ManageDebtRepository
import com.htolintino.mydebts.manageDebt.viewModel.ManageDebtViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val manageDebtModules = module {

    single {
        ManageDebtRepository(get()) as IManageDebtRepository
    }

    single {
        ManageDebtDataModel() as IManageDebtDataModel
    }

    viewModel {
        ManageDebtViewModel(get(), get())
    }

}
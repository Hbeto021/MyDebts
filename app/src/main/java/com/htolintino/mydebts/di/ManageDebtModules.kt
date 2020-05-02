package com.htolintino.mydebts.di

import com.htolintino.mydebts.features.manageDebt.dataModel.IManageDebtDataModel
import com.htolintino.mydebts.features.manageDebt.dataModel.ManageDebtDataModel
import com.htolintino.mydebts.features.manageDebt.repository.IManageDebtRepository
import com.htolintino.mydebts.features.manageDebt.repository.ManageDebtRepository
import com.htolintino.mydebts.features.manageDebt.viewModel.ManageDebtViewModel
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
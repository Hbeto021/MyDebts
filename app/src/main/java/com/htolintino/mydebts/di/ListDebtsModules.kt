package com.htolintino.mydebts.di

import com.htolintino.mydebts.database.AppDatabase
import com.htolintino.mydebts.listDebts.repository.IListDebtsRepository
import com.htolintino.mydebts.listDebts.repository.ListDebtsRepository
import com.htolintino.mydebts.listDebts.viewModel.ListDebtsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val listDebtsModules = module {

    viewModel {
        ListDebtsViewModel(get())
    }

    single {
        ListDebtsRepository(get()) as IListDebtsRepository
    }

}
package com.htolintino.mydebts.di

import com.htolintino.mydebts.database.AppDatabase
import org.koin.dsl.module

val appModules = module {

    single {
        AppDatabase.build(get())
    }

    single {
        get<AppDatabase>().debtDao()
    }
}
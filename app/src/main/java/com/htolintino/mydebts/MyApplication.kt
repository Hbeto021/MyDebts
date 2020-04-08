package com.htolintino.mydebts

import android.app.Application
import com.htolintino.mydebts.di.appModules
import com.htolintino.mydebts.di.listDebtsModules
import com.htolintino.mydebts.di.manageDebtModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModules, listDebtsModules, manageDebtModules))
        }

    }

}
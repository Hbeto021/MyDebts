package com.htolintino.mydebts.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.htolintino.mydebts.dao.DebtDao
import com.htolintino.mydebts.commons.domain.entity.Debt

@Database(entities = [Debt::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun debtDao(): DebtDao

    companion object {
        private const val DB_NAME = "my-debts-database"

        fun build(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME).build()
    }
}
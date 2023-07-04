package com.example.codechallenge

import android.app.Application
import androidx.room.Room
import com.example.codechallenge.main.data.db.TableDataBase

class TableApplication : Application() {

    lateinit var db: TableDataBase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext, TableDataBase::class.java, "table_db"
        ).build()

    }

    fun getDataBase(): TableDataBase {
        return db
    }
}
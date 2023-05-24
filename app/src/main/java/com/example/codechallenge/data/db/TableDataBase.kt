package com.example.codechallenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codechallenge.data.dao.GraphicDao
import com.example.codechallenge.data.dao.TableDao
import com.example.codechallenge.data.entities.GraphicItem
import com.example.codechallenge.data.entities.TableItem

@Database(entities = [TableItem::class, GraphicItem::class], version = 1)
abstract class TableDataBase: RoomDatabase() {
    abstract fun tableDao(): TableDao
    abstract fun graphicDao(): GraphicDao
}
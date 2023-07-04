package com.example.codechallenge.main.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codechallenge.graphic.data.dao.GraphicDao
import com.example.codechallenge.home.data.dao.TableDao
import com.example.codechallenge.graphic.data.entities.GraphicItem
import com.example.codechallenge.home.data.entities.TableItem

@Database(entities = [TableItem::class, GraphicItem::class], version = 1)
abstract class TableDataBase: RoomDatabase() {
    abstract fun tableDao(): TableDao
    abstract fun graphicDao(): GraphicDao
}
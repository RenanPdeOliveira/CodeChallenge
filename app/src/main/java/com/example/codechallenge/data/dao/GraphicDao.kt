package com.example.codechallenge.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codechallenge.data.entities.GraphicItem

@Dao
interface GraphicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: GraphicItem)

    @Query("SELECT * FROM GraphicItem")
    fun getAll(): LiveData<List<GraphicItem>>

    @Query("SELECT SUM(totalLitros) FROM GraphicItem")
    fun getTotalLitros(): Double

    @Query("SELECT SUM(totalAnimais) FROM GraphicItem")
    fun getTotalAnimais(): Int

    @Query("SELECT AVG(primeiraOrdenha) FROM GraphicItem")
    fun getMediaPrimeira(): Int

    @Query("SELECT AVG(segundaOrdenha) FROM GraphicItem")
    fun getMediaSegunda(): Int
}
package com.example.codechallenge.graphic.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codechallenge.graphic.data.entities.GraphicItem

@Dao
interface GraphicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: GraphicItem)

    @Query("SELECT * FROM GraphicItem")
    fun getAll(): LiveData<List<GraphicItem>>

    @Query("SELECT SUM(totalLitros) FROM GraphicItem")
    fun getTotalLitros(): LiveData<Double>

    @Query("SELECT SUM(totalAnimais) FROM GraphicItem")
    fun getTotalAnimais(): LiveData<Int>

    @Query("SELECT AVG(primeiraOrdenha) FROM GraphicItem")
    fun getMediaPrimeira(): LiveData<Int>

    @Query("SELECT AVG(segundaOrdenha) FROM GraphicItem")
    fun getMediaSegunda(): LiveData<Int>
}
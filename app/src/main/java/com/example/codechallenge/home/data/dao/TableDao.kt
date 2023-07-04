package com.example.codechallenge.home.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codechallenge.home.data.entities.TableItem

@Dao
interface TableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: TableItem)

    @Query("SELECT * FROM TableItem")
    fun getAll(): LiveData<List<TableItem>>

    @Query("SELECT SUM(total) FROM TableItem")
    fun getTotal(): LiveData<Float>

    @Query("SELECT MAX(primeiro) FROM TableItem")
    fun getPrimeiraMax(): LiveData<Float>

    @Query("SELECT MIN(primeiro) FROM TableItem")
    fun getPrimeiraMin(): LiveData<Float>

    @Query("SELECT MAX(segundo) FROM TableItem")
    fun getSegundaMax(): LiveData<Float>

    @Query("SELECT MIN(segundo) FROM TableItem")
    fun getSegundaMin(): LiveData<Float>
}
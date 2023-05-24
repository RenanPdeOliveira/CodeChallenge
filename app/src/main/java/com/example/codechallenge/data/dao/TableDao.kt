package com.example.codechallenge.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codechallenge.data.entities.TableItem

@Dao
interface TableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: TableItem)

    @Query("SELECT * FROM TableItem")
    fun getAll(): LiveData<List<TableItem>>

    @Query("SELECT SUM(total) FROM TableItem")
    fun getTotal(): Float

    @Query("SELECT MAX(primeiro) FROM TableItem")
    fun getPrimeiraMax(): Float

    @Query("SELECT MIN(primeiro) FROM TableItem")
    fun getPrimeiraMin(): Float

    @Query("SELECT MAX(segundo) FROM TableItem")
    fun getSegundaMax(): Float

    @Query("SELECT MIN(segundo) FROM TableItem")
    fun getSegundaMin(): Float
}
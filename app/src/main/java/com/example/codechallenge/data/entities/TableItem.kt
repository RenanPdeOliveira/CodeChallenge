package com.example.codechallenge.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class TableItem(
    @PrimaryKey val id: String,
    val microChip: Long,
    val animal: Int,
    val nome: String,
    val dataParto: String,
    val baia: Int,
    val primeiro: Double,
    val segundo: Double,
    val total: Double,
    val dataControle: String,
    val del: Int,
    val obs: String
) : Serializable
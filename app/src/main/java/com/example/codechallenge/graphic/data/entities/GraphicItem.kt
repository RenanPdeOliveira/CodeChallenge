package com.example.codechallenge.graphic.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class GraphicItem(
    @PrimaryKey val id: String,
    val totalAnimais: Int,
    val primeiraOrdenha: Int,
    val segundaOrdenha: Int,
    val totalLitros: Int,
    val media: Double,
    val data: String
) : Serializable

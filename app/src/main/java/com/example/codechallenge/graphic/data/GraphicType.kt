package com.example.codechallenge.graphic.data

import com.example.codechallenge.graphic.data.entities.GraphicItem
import java.io.Serializable

data class GraphicType(
    val item: GraphicItem,
    val actionType: String
) : Serializable

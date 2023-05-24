package com.example.codechallenge.data

import com.example.codechallenge.data.entities.GraphicItem
import java.io.Serializable

data class GraphicType(
    val item: GraphicItem,
    val actionType: String
) : Serializable

package com.example.codechallenge.home.data

import com.example.codechallenge.home.data.entities.TableItem
import java.io.Serializable

data class TableType(
    val item: TableItem,
    val actionType: String
) : Serializable

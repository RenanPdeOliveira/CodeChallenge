package com.example.codechallenge.data

import com.example.codechallenge.data.entities.TableItem
import java.io.Serializable

data class TableType(
    val item: TableItem,
    val actionType: String
) : Serializable

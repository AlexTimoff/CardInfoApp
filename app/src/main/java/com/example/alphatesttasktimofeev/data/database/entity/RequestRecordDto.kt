package com.example.alphatesttasktimofeev.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "allRequests")
data class RequestRecordDto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "bin")
    val bin: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "bank")
    val bank: String
)
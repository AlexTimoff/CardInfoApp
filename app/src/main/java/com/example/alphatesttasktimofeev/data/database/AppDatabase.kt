package com.example.alphatesttasktimofeev.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alphatesttasktimofeev.data.database.entity.RequestRecordDto

@Database(entities = [RequestRecordDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}
package com.example.alphatesttasktimofeev.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.alphatesttasktimofeev.data.database.entity.RequestRecordDto

@Dao
interface CardDao {
    @Query("SELECT * FROM allRequests")
    suspend fun getAllRequests(): List<RequestRecordDto>

    @Insert(entity = RequestRecordDto::class)
    suspend fun insert(requestDB: RequestRecordDto)

    @Query("DELETE FROM allRequests")
    suspend fun deleteAll()
}
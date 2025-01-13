package com.example.alphatesttasktimofeev.domain

import com.example.alphatesttasktimofeev.domain.entity.RequestRecord

interface DataBaseRepository {
    suspend fun getRequests(): List<RequestRecord>

    suspend fun deleteRequests()

    suspend fun addRequest(requestRecord: RequestRecord)
}
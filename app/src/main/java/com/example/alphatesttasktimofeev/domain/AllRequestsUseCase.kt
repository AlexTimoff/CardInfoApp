package com.example.alphatesttasktimofeev.domain

import com.example.alphatesttasktimofeev.domain.entity.RequestRecord
import javax.inject.Inject

class AllRequestsUseCase @Inject constructor(
    private val dataBaseRepository: DataBaseRepository
) {
    suspend fun getAllRequests(): List<RequestRecord> {
        return dataBaseRepository.getRequests()
    }

    suspend fun deleteRequests() {
        dataBaseRepository.deleteRequests()
    }

    suspend fun addRequest(requestRecord: RequestRecord) {
        dataBaseRepository.addRequest(requestRecord)
    }
}
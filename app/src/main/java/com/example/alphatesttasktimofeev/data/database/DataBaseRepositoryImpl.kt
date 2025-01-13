package com.example.alphatesttasktimofeev.data.database

import com.example.alphatesttasktimofeev.domain.DataBaseRepository
import com.example.alphatesttasktimofeev.domain.entity.RequestRecord
import com.example.alphatesttasktimofeev.domain.mapListRequestRecordDtoToListRequestRecord
import com.example.alphatesttasktimofeev.domain.mapRequestRecordToRequestRecordDto
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor(
    private val cardDao: CardDao
) : DataBaseRepository {
    override suspend fun getRequests(): List<RequestRecord> {
        return mapListRequestRecordDtoToListRequestRecord(cardDao.getAllRequests())
    }

    override suspend fun deleteRequests() {
        cardDao.deleteAll()
    }

    override suspend fun addRequest(requestRecord: RequestRecord) {
        cardDao.insert(mapRequestRecordToRequestRecordDto(requestRecord))
    }
}
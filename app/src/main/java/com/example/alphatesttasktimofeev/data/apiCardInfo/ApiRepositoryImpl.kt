package com.example.alphatesttasktimofeev.data.apiCardInfo

import com.example.alphatesttasktimofeev.domain.ApiRepository
import com.example.alphatesttasktimofeev.domain.entity.CardInfo
import com.example.alphatesttasktimofeev.domain.mapCardInfoDtoToCardInfo
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val dataSource: CardInfoDataSource
) : ApiRepository {
    override suspend fun fetchCardInfo(bin: String): CardInfo? {
        val cardInfoDto = dataSource.getCardInfo(bin)
        return if (cardInfoDto == null) null else mapCardInfoDtoToCardInfo(cardInfoDto)
    }
}
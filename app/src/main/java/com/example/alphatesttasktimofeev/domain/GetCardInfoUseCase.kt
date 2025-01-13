package com.example.alphatesttasktimofeev.domain

import com.example.alphatesttasktimofeev.domain.entity.CardInfo
import javax.inject.Inject

class GetCardInfoUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    suspend fun fetchCardInfo(bin: String): CardInfo? {
        return apiRepository.fetchCardInfo(bin)
    }
}
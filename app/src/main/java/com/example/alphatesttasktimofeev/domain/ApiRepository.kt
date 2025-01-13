package com.example.alphatesttasktimofeev.domain

import com.example.alphatesttasktimofeev.domain.entity.CardInfo

interface ApiRepository {
    suspend fun fetchCardInfo(bin: String): CardInfo?
}
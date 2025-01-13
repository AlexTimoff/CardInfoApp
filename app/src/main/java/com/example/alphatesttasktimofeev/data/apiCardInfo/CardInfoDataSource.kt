package com.example.alphatesttasktimofeev.data.apiCardInfo

import android.util.Log
import com.example.alphatesttasktimofeev.data.apiCardInfo.entity.CardInfoDto
import javax.inject.Inject

class CardInfoDataSource @Inject constructor(private val api: GetCardInfoResponse) {
    suspend fun getCardInfo(bin: String): CardInfoDto? {
        try {
            val response = api.getCardInfo(bin)
            if (response.isSuccessful) {
                return response.body()!!
            }
        } catch (ex: Exception) {
            Log.d("CardInfoDataSource", "${ex.message}")
        }
        return null
    }
}
package com.example.alphatesttasktimofeev.data.apiCardInfo

import com.example.alphatesttasktimofeev.data.apiCardInfo.entity.CardInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GetCardInfoResponse {
    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun getCardInfo(
        @Path("bin") bin: String
    ): Response<CardInfoDto>
}
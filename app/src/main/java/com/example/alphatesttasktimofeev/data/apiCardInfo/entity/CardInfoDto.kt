package com.example.alphatesttasktimofeev.data.apiCardInfo.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CardInfoDto(
    @Json(name = "scheme") val scheme: String?,
    @Json(name = "country") val country: CountryInfoDto?,
    @Json(name = "bank") val bank: BankInfoDto?
)




package com.example.alphatesttasktimofeev.data.apiCardInfo.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryInfoDto(
    @Json(name = "name") val name: String?,
    @Json(name = "latitude") val latitude: Int?,
    @Json(name = "longitude") val longitude: Int?
)
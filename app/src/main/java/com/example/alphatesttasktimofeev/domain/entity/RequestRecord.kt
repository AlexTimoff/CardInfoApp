package com.example.alphatesttasktimofeev.domain.entity

data class RequestRecord(
    val id: Int,
    val bin: String,
    val type: String,
    val country: String,
    val bank: String
)
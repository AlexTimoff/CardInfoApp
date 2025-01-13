package com.example.alphatesttasktimofeev.domain

import com.example.alphatesttasktimofeev.data.apiCardInfo.entity.CardInfoDto
import com.example.alphatesttasktimofeev.data.database.entity.RequestRecordDto
import com.example.alphatesttasktimofeev.domain.entity.BankInfo
import com.example.alphatesttasktimofeev.domain.entity.CardInfo
import com.example.alphatesttasktimofeev.domain.entity.CountryInfo
import com.example.alphatesttasktimofeev.domain.entity.RequestRecord


fun mapCardInfoDtoToCardInfo(cardInfoDto: CardInfoDto): CardInfo {
    return CardInfo(
        scheme = cardInfoDto.scheme,
        country = CountryInfo(
            name = cardInfoDto.country?.name,
            latitude = cardInfoDto.country?.latitude,
            longitude = cardInfoDto.country?.longitude
        ),
        bank = BankInfo(
            name = cardInfoDto.bank?.name,
            url = cardInfoDto.bank?.url,
            phone = cardInfoDto.bank?.phone,
            city = cardInfoDto.bank?.city,
        )
    )
}

fun mapListRequestRecordDtoToListRequestRecord(listRequestRecordDto: List<RequestRecordDto>): List<RequestRecord> {
    val listRequestRecord = ArrayList<RequestRecord>()
    for (requestRecordDto in listRequestRecordDto) {
        val requestRecord = RequestRecord(
            id = requestRecordDto.id,
            bin = requestRecordDto.bin,
            type = requestRecordDto.type,
            country = requestRecordDto.country,
            bank = requestRecordDto.bank
        )
        listRequestRecord.add(requestRecord)
    }
    return listRequestRecord.toList()
}

fun mapRequestRecordToRequestRecordDto(requestRecord: RequestRecord): RequestRecordDto {
    return RequestRecordDto(
        id = requestRecord.id,
        bin = requestRecord.bin,
        type = requestRecord.type,
        country = requestRecord.country,
        bank = requestRecord.bank
    )
}
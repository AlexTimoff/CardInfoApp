package com.example.alphatesttasktimofeev.di

import com.example.alphatesttasktimofeev.App
import com.example.alphatesttasktimofeev.data.apiCardInfo.GetCardInfoResponse
import com.example.alphatesttasktimofeev.data.database.CardDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://lookup.binlist.net/"

@Module
class DataSourceModule {

    @Provides
    fun provideCardDao(): CardDao {
        return App.db.cardDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): GetCardInfoResponse {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(GetCardInfoResponse::class.java)
    }
}
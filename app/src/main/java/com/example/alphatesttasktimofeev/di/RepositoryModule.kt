package com.example.alphatesttasktimofeev.di

import com.example.alphatesttasktimofeev.data.apiCardInfo.ApiRepositoryImpl
import com.example.alphatesttasktimofeev.data.database.DataBaseRepositoryImpl
import com.example.alphatesttasktimofeev.domain.ApiRepository
import com.example.alphatesttasktimofeev.domain.DataBaseRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindsApiRepository(repository: ApiRepositoryImpl): ApiRepository

    @Binds
    fun bindsDataBaseRepository(repository: DataBaseRepositoryImpl): DataBaseRepository

}
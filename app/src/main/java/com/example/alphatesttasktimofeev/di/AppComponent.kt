package com.example.alphatesttasktimofeev.di

import com.example.alphatesttasktimofeev.presentation.cardInfo.fragment.InformationFragment
import com.example.alphatesttasktimofeev.presentation.requestsList.fragment.RequestsFragment
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        DataSourceModule::class,
        RepositoryModule::class
    ]
)

@Singleton
interface AppComponent {
    fun injectInformationFragment(fragment: InformationFragment)
    fun injectRequestFragment(fragment: RequestsFragment)
}
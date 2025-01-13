package com.example.alphatesttasktimofeev

import android.app.Application
import androidx.room.Room
import com.example.alphatesttasktimofeev.data.database.AppDatabase
import com.example.alphatesttasktimofeev.di.AppComponent
import com.example.alphatesttasktimofeev.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        daggerAppComponent = DaggerAppComponent.create()

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "db"
        ).build()
    }

    companion object {
        lateinit var db: AppDatabase
        lateinit var daggerAppComponent: AppComponent
    }
}
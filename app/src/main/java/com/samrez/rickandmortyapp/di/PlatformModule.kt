package com.samrez.rickandmortyapp.di

import androidx.room.Room
import com.samrez.rickandmortyapp.core.database.AppDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val platformModule = module {
    single<HttpClientEngine> { OkHttp.create() }
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "rick_morty.db"
        ).fallbackToDestructiveMigration(true).build()
    }
}

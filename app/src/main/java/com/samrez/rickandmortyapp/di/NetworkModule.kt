package com.samrez.rickandmortyapp.di

import com.samrez.rickandmortyapp.core.network.HttpClientFactory
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
        }
    }
    single { HttpClientFactory.create(engine = get(), json = get()) }
}

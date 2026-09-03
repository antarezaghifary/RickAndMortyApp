package com.samrez.rickandmortyapp

import android.app.Application
import com.samrez.rickandmortyapp.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class RickMortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initializeKoin {
            androidContext(this@RickMortyApplication)
        }
    }
}


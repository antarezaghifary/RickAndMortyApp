package com.samrez.rickandmortyapp.di

import com.samrez.rickandmortyapp.features.characters.di.characterModule
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

val appModules: List<Module> = listOf(
    platformModule,
    networkModule,
    databaseModule,
    characterModule
)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = initializeKoin(appDeclaration)


package com.samrez.rickandmortyapp.di

import com.samrez.rickandmortyapp.core.database.AppDatabase
import com.samrez.rickandmortyapp.core.database.dao.CharacterDao
import com.samrez.rickandmortyapp.core.database.dao.EpisodeDao
import org.koin.dsl.module

val databaseModule = module {
    single<CharacterDao> { get<AppDatabase>().characterDao() }
    single<EpisodeDao> { get<AppDatabase>().episodeDao() }
}

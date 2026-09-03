package com.samrez.rickandmortyapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samrez.rickandmortyapp.core.database.dao.CharacterDao
import com.samrez.rickandmortyapp.core.database.dao.EpisodeDao
import com.samrez.rickandmortyapp.core.database.entity.CharacterFavoriteEntity
import com.samrez.rickandmortyapp.core.database.entity.EpisodeFavoriteEntity

@Database(
    entities = [
        CharacterFavoriteEntity::class,
        EpisodeFavoriteEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
}

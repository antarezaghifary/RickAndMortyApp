package com.samrez.rickandmortyapp.features.characters.domain.repository

import com.samrez.rickandmortyapp.core.utils.DataError
import com.samrez.rickandmortyapp.features.characters.domain.model.Character
import com.samrez.rickandmortyapp.features.characters.domain.model.CharacterFilter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(
        page: Int = 1,
        filter: CharacterFilter? = null
    ): com.samrez.rickandmortyapp.core.utils.Result<List<Character>, DataError.Network>

    suspend fun getCharacter(id: Int): com.samrez.rickandmortyapp.core.utils.Result<Character, DataError.Network>

    suspend fun getMultipleCharacters(ids: List<Int>): com.samrez.rickandmortyapp.core.utils.Result<List<Character>, DataError.Network>

    fun getFavorites(): Flow<List<Character>>

    fun isFavorite(id: Int): Flow<Boolean>

    suspend fun toggleFavorite(character: Character)
}

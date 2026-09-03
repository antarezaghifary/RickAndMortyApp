package com.samrez.rickandmortyapp.features.characters.data.repository

import com.samrez.rickandmortyapp.features.characters.data.mapper.toDomain
import com.samrez.rickandmortyapp.features.characters.data.mapper.toFavoriteEntity
import com.samrez.rickandmortyapp.features.characters.data.remote.CharacterApi
import com.samrez.rickandmortyapp.features.characters.domain.model.CharacterFilter
import com.samrez.rickandmortyapp.features.characters.domain.repository.CharacterRepository
import com.samrez.rickandmortyapp.core.database.dao.CharacterDao
import com.samrez.rickandmortyapp.core.utils.DataError
import com.samrez.rickandmortyapp.core.utils.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CharacterRepositoryImpl(
    private val api: CharacterApi,
    private val characterDao: CharacterDao
) : CharacterRepository {

    override suspend fun getCharacters(
        page: Int,
        filter: CharacterFilter?
    ): com.samrez.rickandmortyapp.core.utils.Result<List<com.samrez.rickandmortyapp.features.characters.domain.model.Character>, DataError.Network> {
        return api.getCharacters(
            page = page,
            name = filter?.name,
            status = filter?.status?.name?.lowercase(),
            species = filter?.species,
            type = filter?.type,
            gender = filter?.gender?.name?.lowercase()
        ).map { responseDto ->
            responseDto.results.map { it.toDomain() }
        }
    }

    override suspend fun getCharacter(id: Int): com.samrez.rickandmortyapp.core.utils.Result<com.samrez.rickandmortyapp.features.characters.domain.model.Character, DataError.Network> {
        return api.getCharacter(id).map { it.toDomain() }
    }

    override suspend fun getMultipleCharacters(ids: List<Int>): com.samrez.rickandmortyapp.core.utils.Result<List<com.samrez.rickandmortyapp.features.characters.domain.model.Character>, DataError.Network> {
        return api.getMultipleCharacters(ids).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getFavorites(): Flow<List<com.samrez.rickandmortyapp.features.characters.domain.model.Character>> {
        return characterDao.getAllFavorites().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun isFavorite(id: Int): Flow<Boolean> {
        return characterDao.isFavorite(id)
    }

    override suspend fun toggleFavorite(character: com.samrez.rickandmortyapp.features.characters.domain.model.Character) {
        val existing = characterDao.getById(character.id)
        if (existing != null) {
            characterDao.deleteById(character.id)
        } else {
            characterDao.insert(character.toFavoriteEntity())
        }
    }
}

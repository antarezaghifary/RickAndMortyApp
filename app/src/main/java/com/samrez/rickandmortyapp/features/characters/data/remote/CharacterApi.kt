package com.samrez.rickandmortyapp.features.characters.data.remote

import com.samrez.rickandmortyapp.core.network.safeApiCall
import com.samrez.rickandmortyapp.core.utils.DataError
import com.samrez.rickandmortyapp.core.utils.map
import com.samrez.rickandmortyapp.features.characters.data.model.CharacterDto
import com.samrez.rickandmortyapp.features.characters.data.model.CharacterResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class CharacterApi(
    private val httpClient: HttpClient
) {
    suspend fun getCharacters(
        page: Int = 1,
        name: String? = null,
        status: String? = null,
        species: String? = null,
        type: String? = null,
        gender: String? = null
    ): com.samrez.rickandmortyapp.core.utils.Result<CharacterResponseDto, DataError.Network> {
        return safeApiCall {
            httpClient.get("character") {
                parameter("page", page)
                name?.takeIf { it.isNotBlank() }?.let { parameter("name", it) }
                status?.takeIf { it.isNotBlank() }?.let { parameter("status", it) }
                species?.takeIf { it.isNotBlank() }?.let { parameter("species", it) }
                type?.takeIf { it.isNotBlank() }?.let { parameter("type", it) }
                gender?.takeIf { it.isNotBlank() }?.let { parameter("gender", it) }
            }
        }
    }

    suspend fun getCharacter(id: Int): com.samrez.rickandmortyapp.core.utils.Result<CharacterDto, DataError.Network> {
        return safeApiCall {
            httpClient.get("character/$id")
        }
    }

    suspend fun getMultipleCharacters(ids: List<Int>): com.samrez.rickandmortyapp.core.utils.Result<List<CharacterDto>, DataError.Network> {
        if (ids.isEmpty()) {
            return com.samrez.rickandmortyapp.core.utils.Result.Success(emptyList())
        }
        if (ids.size == 1) {
            return getCharacter(ids.first()).map { listOf(it) }
        }
        return safeApiCall {
            httpClient.get("character/${ids.joinToString(",")}")
        }
    }
}

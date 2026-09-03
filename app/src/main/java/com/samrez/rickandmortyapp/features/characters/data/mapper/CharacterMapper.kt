@file:OptIn(ExperimentalTime::class)

package com.samrez.rickandmortyapp.features.characters.data.mapper

import com.samrez.rickandmortyapp.core.database.entity.CharacterFavoriteEntity
import com.samrez.rickandmortyapp.features.characters.data.model.CharacterDto
import com.samrez.rickandmortyapp.features.characters.domain.model.CharacterGender
import com.samrez.rickandmortyapp.features.characters.domain.model.CharacterStatus
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun CharacterDto.toDomain(): com.samrez.rickandmortyapp.features.characters.domain.model.Character {
    return com.samrez.rickandmortyapp.features.characters.domain.model.Character(
        id = id,
        name = name,
        status = CharacterStatus.fromString(status),
        species = species,
        type = type,
        gender = CharacterGender.fromString(gender),
        originName = origin.name,
        locationName = location.name,
        imageUrl = image,
        episodeIds = episode.mapNotNull { it.trimEnd('/').substringAfterLast('/').toIntOrNull() }
    )
}

fun CharacterFavoriteEntity.toDomain(): com.samrez.rickandmortyapp.features.characters.domain.model.Character {
    return com.samrez.rickandmortyapp.features.characters.domain.model.Character(
        id = id,
        name = name,
        status = CharacterStatus.fromString(status),
        species = species,
        type = "",
        gender = CharacterGender.fromString(gender),
        originName = originName,
        locationName = locationName,
        imageUrl = image,
        episodeIds = emptyList()
    )
}

fun CharacterDto.toFavoriteEntity(
    createdAt: Long = Clock.System.now().toEpochMilliseconds()
): CharacterFavoriteEntity {
    return CharacterFavoriteEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
        originName = origin.name,
        locationName = location.name,
        createdAt = createdAt
    )
}

fun com.samrez.rickandmortyapp.features.characters.domain.model.Character.toFavoriteEntity(
    createdAt: Long = Clock.System.now().toEpochMilliseconds()
): CharacterFavoriteEntity {
    return CharacterFavoriteEntity(
        id = id,
        name = name,
        status = status.displayName,
        species = species,
        gender = gender.displayName,
        image = imageUrl,
        originName = originName,
        locationName = locationName,
        createdAt = createdAt
    )
}


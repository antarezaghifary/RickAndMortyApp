package com.samrez.rickandmortyapp.features.characters.domain.usecase

import com.samrez.rickandmortyapp.features.characters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class IsCharacterFavoriteUseCase(
    private val repository: CharacterRepository
) {
    operator fun invoke(id: Int): Flow<Boolean> {
        return repository.isFavorite(id)
    }
}

package com.samrez.rickandmortyapp.features.characters.domain.usecase

import com.samrez.rickandmortyapp.features.characters.domain.model.Character
import com.samrez.rickandmortyapp.features.characters.domain.repository.CharacterRepository

class ToggleCharacterFavoriteUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(character: Character) {
        repository.toggleFavorite(character)
    }
}

package com.samrez.rickandmortyapp.features.characters.domain.usecase

import com.samrez.rickandmortyapp.core.utils.DataError
import com.samrez.rickandmortyapp.features.characters.domain.model.Character
import com.samrez.rickandmortyapp.features.characters.domain.repository.CharacterRepository

class GetCharacterDetailUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): com.samrez.rickandmortyapp.core.utils.Result<Character, DataError.Network> {
        return repository.getCharacter(id)
    }
}

package com.samrez.rickandmortyapp.features.characters.domain.usecase

import com.samrez.rickandmortyapp.core.utils.DataError
import com.samrez.rickandmortyapp.features.characters.domain.model.Character
import com.samrez.rickandmortyapp.features.characters.domain.model.CharacterFilter
import com.samrez.rickandmortyapp.features.characters.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        filter: CharacterFilter? = null
    ): com.samrez.rickandmortyapp.core.utils.Result<List<Character>, DataError.Network> {
        return repository.getCharacters(page = page, filter = filter)
    }
}

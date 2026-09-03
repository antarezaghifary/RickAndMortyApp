package com.samrez.rickandmortyapp.features.characters.di

import com.samrez.rickandmortyapp.features.characters.presentation.list.CharactersViewModel
import com.samrez.rickandmortyapp.features.characters.data.remote.CharacterApi
import com.samrez.rickandmortyapp.features.characters.data.repository.CharacterRepositoryImpl
import com.samrez.rickandmortyapp.features.characters.domain.repository.CharacterRepository
import com.samrez.rickandmortyapp.features.characters.domain.usecase.GetCharacterDetailUseCase
import com.samrez.rickandmortyapp.features.characters.domain.usecase.GetCharactersUseCase
import com.samrez.rickandmortyapp.features.characters.domain.usecase.GetFavoriteCharactersUseCase
import com.samrez.rickandmortyapp.features.characters.domain.usecase.IsCharacterFavoriteUseCase
import com.samrez.rickandmortyapp.features.characters.domain.usecase.ToggleCharacterFavoriteUseCase
import com.samrez.rickandmortyapp.features.characters.presentation.detail.CharacterDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val characterModule = module {
    single { CharacterApi(httpClient = get()) }
    single<CharacterRepository> { CharacterRepositoryImpl(api = get(), characterDao = get()) }

    single { GetCharactersUseCase(repository = get()) }
    single { GetCharacterDetailUseCase(repository = get()) }
    single { ToggleCharacterFavoriteUseCase(repository = get()) }
    single { GetFavoriteCharactersUseCase(repository = get()) }
    single { IsCharacterFavoriteUseCase(repository = get()) }

    viewModelOf(::CharactersViewModel)
    viewModel { (characterId: Int) ->
        CharacterDetailViewModel(
            characterId = characterId,
            getCharacterDetailUseCase = get(),
            toggleFavoriteUseCase = get(),
            isFavoriteUseCase = get()
        )
    }
}

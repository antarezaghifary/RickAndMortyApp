package com.samrez.rickandmortyapp.features.characters.presentation.detail

import androidx.compose.runtime.Immutable

@Immutable
data class CharacterDetailState(
    val character: com.samrez.rickandmortyapp.features.characters.domain.model.Character? = null,
    val isLoading: Boolean = true,
    val isFavorite: Boolean = false,
    val errorMessage: String? = null
)

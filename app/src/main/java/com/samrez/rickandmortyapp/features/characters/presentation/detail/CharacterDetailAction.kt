package com.samrez.rickandmortyapp.features.characters.presentation.detail

sealed interface CharacterDetailAction {
    data object OnBackClick : CharacterDetailAction
    data object OnToggleFavorite : CharacterDetailAction
    data object OnRetry : CharacterDetailAction
    data class OnEpisodeClick(val episodeId: Int) : CharacterDetailAction
}

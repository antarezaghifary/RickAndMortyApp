package com.samrez.rickandmortyapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.samrez.rickandmortyapp.navigation.RickMortyNavHost
import com.samrez.rickandmortyapp.core.designsystem.theme.RickMortyTheme
import com.samrez.rickandmortyapp.core.designsystem.theme.SpaceBlack

@Composable
fun App() {
    RickMortyTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = SpaceBlack
        ) {
            RickMortyNavHost()
        }
    }
}

package com.mozzartinterview.feature_kino_game.ui.kino_game_details

import com.mozzartinterview.feature_kino_game.ui.kino_games.KinoGameItem
import com.mozzartinterview.feature_kino_game.ui.talon.KinoTalonItem

data class KinoGameDetailsUiState(
    val kinoGameItem: KinoGameItem,
    val talonList: List<KinoTalonItem>,
    val selectedTalonList: List<KinoTalonItem>,
)
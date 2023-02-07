package com.mozzartinterview.feature_kino_game.ui.results

import com.mozzartinterview.feature_kino_game.data.model.WinningNumbers

data class KinoResultItem(
    val winningNumbers: WinningNumbers,
    val drawTime: Long,
    val drawId: Int
)
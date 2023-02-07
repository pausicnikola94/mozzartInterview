package com.mozzartinterview.feature_kino_game.data.model

data class Content(
    val drawBreak: Int,
    val drawId: Int,
    val drawTime: Long,
    val gameId: Int,
    val pricePoints: Any,
    val prizeCategories: List<Any>,
    val status: String,
    val visualDraw: Int,
    val wagerStatistics: Any,
    val winningNumbers: WinningNumbers
)
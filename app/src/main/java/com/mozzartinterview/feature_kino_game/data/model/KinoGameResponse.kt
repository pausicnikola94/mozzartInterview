package com.mozzartinterview.feature_kino_game.data.model

import com.mozzartinterview.feature_kino_game.ui.kino_games.KinoGameItem

data class KinoGameResponse(
    val drawBreak: Int,
    val drawId: Int,
    val drawTime: Long,
    val gameId: Int,
    val pricePoints: Any,
    val prizeCategories: List<Any>,
    val status: String,
    val visualDraw: Int,
    val wagerStatistics: Any
)

fun KinoGameResponse.toKinoGameItem(): KinoGameItem {
    return KinoGameItem(this.drawId, this.drawTime, this.gameId, 0)
}
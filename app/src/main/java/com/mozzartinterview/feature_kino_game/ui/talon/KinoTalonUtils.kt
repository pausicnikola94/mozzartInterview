package com.mozzartinterview.feature_kino_game.ui.talon

fun createTalon(): List<KinoTalonItem> {
    return (1..80).map { KinoTalonItem(it) }
}
package com.mozzartinterview.feature_kino_game.data.data_source

import com.mozzartinterview.core.data.BaseDataSource
import com.mozzartinterview.feature_kino_game.data.api.KinoGameApi

class KinoGameRemoteDataSource(
    private val kinoGameApi: KinoGameApi
) : BaseDataSource() {
    suspend fun getGames(gameId: String, amount: String) =
        getResult { kinoGameApi.getGames(gameId, amount) }

    suspend fun getGame(gameId: String, drawId: String) =
        getResult { kinoGameApi.getGame(gameId, drawId) }

    suspend fun getPastGames(gameId: String, fromDate: String, toDate: String) =
        getResult { kinoGameApi.getPastGames(gameId, fromDate, toDate) }
}

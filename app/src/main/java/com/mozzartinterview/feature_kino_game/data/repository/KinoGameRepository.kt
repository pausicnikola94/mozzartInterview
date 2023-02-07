package com.mozzartinterview.feature_kino_game.data.repository

import com.mozzartinterview.core.data.Resource
import com.mozzartinterview.feature_kino_game.data.data_source.KinoGameRemoteDataSource
import com.mozzartinterview.feature_kino_game.data.model.KinoGameResponse
import com.mozzartinterview.feature_kino_game.data.model.KinoPastGamesResponse
import com.mozzartinterview.feature_kino_game.data.model.toKinoGameItem
import com.mozzartinterview.feature_kino_game.ui.kino_games.KinoGameItem
import com.mozzartinterview.feature_kino_game.ui.results.KinoResultItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class KinoGameRepository(
    private val kinoGameRemoteDataSource: KinoGameRemoteDataSource
) {
    fun getGames(gameId: String, amount: String): Flow<Resource<List<KinoGameItem>>> = flow {
        val response = kinoGameRemoteDataSource.getGames(gameId, amount)
        emit(getMappedGames(response))
    }

    fun getGame(gameId: String, drawId: String): Flow<Resource<KinoGameItem>> = flow {
        val response = kinoGameRemoteDataSource.getGame(gameId, drawId)
        emit(getMappedGame(response))
    }

    fun getPastGames(
        gameId: String,
        fromDate: String,
        toDate: String
    ): Flow<Resource<List<KinoResultItem>>> = flow {
        val response = kinoGameRemoteDataSource.getPastGames(gameId, fromDate, toDate)
        emit(getPastMappedGame(response))
    }

    private fun getPastMappedGame(response: Resource<KinoPastGamesResponse>): Resource<List<KinoResultItem>> {
        if (response.status == Resource.Status.SUCCESS) {
            if (response.data != null) {
                val kinoPastGameList = mutableListOf<KinoResultItem>()
                val content = response.data.content

                for (i in 0 until 10) {
                    kinoPastGameList.add(
                        KinoResultItem(
                            content[i].winningNumbers,
                            content[i].drawTime,
                            content[i].drawId
                        )
                    )
                }

                return Resource.success(kinoPastGameList)
            }
        }
        return Resource.error(response.message ?: "Get Kino Past Games Failed")
    }

    private fun getMappedGames(response: Resource<List<KinoGameResponse>>): Resource<List<KinoGameItem>> {
        if (response.status == Resource.Status.SUCCESS) {
            if (response.data != null) {
                val mappedList = response.data.map { it.toKinoGameItem() }
                return Resource.success(mappedList)
            }
        }
        return Resource.error(response.message ?: "Get Kino Games Failed")
    }

    private fun getMappedGame(response: Resource<KinoGameResponse>): Resource<KinoGameItem> {
        if (response.status == Resource.Status.SUCCESS) {
            if (response.data != null) {
                val mapped = response.data.toKinoGameItem()
                return Resource.success(mapped)
            }
        }
        return Resource.error(response.message ?: "Get Kino Game Failed")
    }
}
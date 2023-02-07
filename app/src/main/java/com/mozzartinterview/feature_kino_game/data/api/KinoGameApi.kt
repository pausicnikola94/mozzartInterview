package com.mozzartinterview.feature_kino_game.data.api

import com.mozzartinterview.feature_kino_game.data.model.KinoGameResponse
import com.mozzartinterview.feature_kino_game.data.model.KinoPastGamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface KinoGameApi {
    @GET("draws/v3.0/{gameId}/upcoming/{amount}")
    suspend fun getGames(
        @Path("gameId") gameId: String,
        @Path("amount") amount: String
    ): Response<List<KinoGameResponse>>

    @GET("draws/v3.0/{gameId}/{drawId}")
    suspend fun getGame(
        @Path("gameId") gameId: String,
        @Path("drawId") drawId: String
    ): Response<KinoGameResponse>

    @GET("draws/v3.0/{gameId}/draw-date/{fromDate}/{toDate}")
    suspend fun getPastGames(
        @Path("gameId") gameId: String,
        @Path("fromDate") fromDate: String,
        @Path("toDate") toDate: String
    ): Response<KinoPastGamesResponse>
}
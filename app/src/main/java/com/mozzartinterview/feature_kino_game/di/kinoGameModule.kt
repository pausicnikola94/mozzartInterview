package com.mozzartinterview.feature_kino_game.di

import com.mozzartinterview.feature_kino_game.ui.kino_games.KinoGameViewModel
import com.mozzartinterview.feature_kino_game.data.api.KinoGameApi
import com.mozzartinterview.feature_kino_game.data.data_source.KinoGameRemoteDataSource
import com.mozzartinterview.feature_kino_game.data.repository.KinoGameRepository
import com.mozzartinterview.feature_kino_game.ui.kino_game_details.KinoGameDetailsViewModel
import com.mozzartinterview.feature_kino_game.ui.results.KinoResultsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val kinoGameModule = module {
    viewModel { KinoGameViewModel(get()) }
    viewModel { KinoGameDetailsViewModel(get()) }
    viewModel { KinoResultsViewModel(get()) }

    single { provideLiveApiInterface(get()) }
    single { KinoGameRemoteDataSource(get()) }
    single { KinoGameRepository(get()) }
}

fun provideLiveApiInterface(retrofit: Retrofit): KinoGameApi =
    retrofit.create(KinoGameApi::class.java)
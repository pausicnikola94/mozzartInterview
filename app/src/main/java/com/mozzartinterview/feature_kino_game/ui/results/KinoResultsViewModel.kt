package com.mozzartinterview.feature_kino_game.ui.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mozzartinterview.core.data.Resource
import com.mozzartinterview.core.data.getCurrentTimeAsString
import com.mozzartinterview.feature_kino_game.data.repository.KinoGameRepository
import com.mozzartinterview.feature_kino_game.ui.kino_game_details.KinoGameConstants
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class KinoResultsViewModel(
    private val kinoGameRepository: KinoGameRepository
) : ViewModel() {

    private var _kinoPastGameUiState = MutableStateFlow<KinoResultsUiState>(
        KinoResultsUiState(listOf())
    )
    val kinoPastGameUiState: StateFlow<KinoResultsUiState> = _kinoPastGameUiState

    private var _job: Job? = null

    fun setup() {
        _job?.cancel()
        _job = viewModelScope.launch {
            kinoGameRepository.getPastGames(
                KinoGameConstants.KINO_GAME_ID.toString(),
                getCurrentTimeAsString(),
                getCurrentTimeAsString()
            )
                .collect { pastGamesResponse ->
                    if (pastGamesResponse.status == Resource.Status.SUCCESS) {
                        if (pastGamesResponse.data == null) return@collect
                        val result = pastGamesResponse.data
                        _kinoPastGameUiState.value = _kinoPastGameUiState.value.copy(
                            resultList = result
                        )
                    }
                }
        }
    }
}
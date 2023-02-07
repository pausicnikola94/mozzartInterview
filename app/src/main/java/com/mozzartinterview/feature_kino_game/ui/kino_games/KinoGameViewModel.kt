package com.mozzartinterview.feature_kino_game.ui.kino_games

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mozzartinterview.core.data.Resource
import com.mozzartinterview.feature_kino_game.data.repository.KinoGameRepository
import com.mozzartinterview.feature_kino_game.ui.kino_game_details.KinoGameConstants
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Instant

class KinoGameViewModel(
    private val kinoGameRepository: KinoGameRepository
) : ViewModel() {

    private var _KinoGameUiState = MutableStateFlow<KinoGameUiState>(
        KinoGameUiState(listOf())
    )
    val kinoGameUiState: StateFlow<KinoGameUiState> = _KinoGameUiState
    private var _refreshJob: Job? = null
    private var _getGamesJob: Job? = null

    fun startRefreshing() {
        _refreshJob?.cancel()
        _refreshJob = viewModelScope.launch {
            delay(1000)
            val result =
                _KinoGameUiState.value.kinoKoloList.map { it.copy(remainingTime = it.remainingTime - 1000) }
            _KinoGameUiState.value = _KinoGameUiState.value.copy(kinoKoloList = result)
            startRefreshing()
        }
    }

    fun getGames() {
        _getGamesJob?.cancel()
        _getGamesJob = viewModelScope.launch {
            kinoGameRepository.getGames(
                KinoGameConstants.KINO_GAME_ID.toString(),
                KinoGameConstants.STANDARD_AMOUNT.toString()
            ).collectLatest { kinoResponse ->
                if (kinoResponse.status == Resource.Status.SUCCESS) {
                    if (kinoResponse.data == null) return@collectLatest
                    val result = kinoResponse.data.map {
                        it.copy(
                            remainingTime = it.drawTime - Instant.now().toEpochMilli()
                        )
                    }
                    _KinoGameUiState.value = _KinoGameUiState.value.copy(kinoKoloList = result)
                }
            }
        }
    }
}
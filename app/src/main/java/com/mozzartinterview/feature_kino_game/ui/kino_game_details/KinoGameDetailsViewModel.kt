package com.mozzartinterview.feature_kino_game.ui.kino_game_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mozzartinterview.feature_kino_game.data.repository.KinoGameRepository
import com.mozzartinterview.feature_kino_game.ui.kino_games.KinoGameItem
import com.mozzartinterview.feature_kino_game.ui.talon.KinoTalonItem
import com.mozzartinterview.feature_kino_game.ui.talon.createTalon
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Instant

class KinoGameDetailsViewModel(
    private val kinoGameRepository: KinoGameRepository
) : ViewModel() {
    private var _kinoGameDetailsUiState = MutableStateFlow<KinoGameDetailsUiState>(
        KinoGameDetailsUiState(KinoGameItem(0,0,0,0), createTalon(), listOf())
    )
    val kinoGameDetailsUiState: StateFlow<KinoGameDetailsUiState> = _kinoGameDetailsUiState

    private var _job: Job? = null
    private var _refreshJob: Job? = null

    fun updateKinoDrawId(drawId: Int) {
        _job?.cancel()
        _job = viewModelScope.launch {
            kinoGameRepository.getGame(
                KinoGameConstants.KINO_GAME_ID.toString(),
                drawId.toString()
            ).collectLatest { kinoGameResponse ->
                if (kinoGameResponse.data == null) return@collectLatest
                _kinoGameDetailsUiState.value = _kinoGameDetailsUiState.value.copy(
                    kinoGameItem = kinoGameResponse.data.copy(
                        remainingTime = kinoGameResponse.data.drawTime - Instant.now().toEpochMilli()
                    )
                )
            }

        }
    }

    fun startRefreshing() {
        _refreshJob?.cancel()
        _refreshJob = viewModelScope.launch {
            delay(1000)
            _kinoGameDetailsUiState.value = _kinoGameDetailsUiState.value.copy(
                kinoGameItem = _kinoGameDetailsUiState.value.kinoGameItem.copy(
                    remainingTime = _kinoGameDetailsUiState.value.kinoGameItem.remainingTime - 1000
                )
            )
            startRefreshing()
        }
    }

    fun talonItemClick(number: Int) {
        val selectedTalonList = mutableListOf<KinoTalonItem>()
        val updatedTalonList = kinoGameDetailsUiState.value.talonList.map {
            val selected = getIsSelected(it, number)
            if (selected) {
                selectedTalonList.add(it.copy(isSelected = selected))
            }
            it.copy(
                isSelected = selected
            )
        }
        _kinoGameDetailsUiState.value = _kinoGameDetailsUiState.value.copy(
            talonList = updatedTalonList,
            selectedTalonList = selectedTalonList
        )
    }
}

private fun getIsSelected(item: KinoTalonItem, number: Int): Boolean {
    if (item.number == number && item.isSelected) {
        return false
    }
    if (item.number == number) {
        return true
    }
    return item.isSelected
}

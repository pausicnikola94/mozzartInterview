package com.mozzartinterview.feature_kino_game.ui.kino_games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.mozzartinterview.R
import com.mozzartinterview.databinding.FragmentKinoGamesBinding
import com.mozzartinterview.feature_kino_game.ui.kino_game_details.KinoGameConstants
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class KinoGamesFragment : Fragment() {

    private var _binding: FragmentKinoGamesBinding? = null
    private val binding get() = _binding!!
    private var _kinoGamesAdapter: KinoGameAdapter? = null
    private val kinoGamesAdapter get() = _kinoGamesAdapter
    private val viewModel: KinoGameViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKinoGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _kinoGamesAdapter = KinoGameAdapter {
            findNavController().navigate(
                R.id.action_KinoGamesFragment_to_KinoGameDetailsFragment,
                bundleOf(Pair(KinoGameConstants.KINO_DRAW_ID_KEY, it))
            )
        }
        binding.kinoGamesRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.kinoGamesRV.adapter = kinoGamesAdapter
        (binding.kinoGamesRV.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        setupUiStateConsumer()

        viewModel.getGames()
        viewModel.startRefreshing()
    }

    private fun setupUiStateConsumer() {
        lifecycleScope.launch {
            activity?.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.kinoGameUiState.collectLatest { kinoGameUiState ->
                    kinoGamesAdapter?.submitList(kinoGameUiState.kinoKoloList)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.kinoGamesRV.adapter = null
        _binding = null
    }

}
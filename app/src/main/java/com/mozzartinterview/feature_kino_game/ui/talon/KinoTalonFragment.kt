package com.mozzartinterview.feature_kino_game.ui.talon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.SimpleItemAnimator
import com.mozzartinterview.core.data.CustomGridLayoutManager
import com.mozzartinterview.databinding.FragmentKinoTalonBinding
import com.mozzartinterview.feature_kino_game.ui.kino_game_details.KinoGameDetailsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class KinoTalonFragment : Fragment() {
    private var _binding: FragmentKinoTalonBinding? = null
    private val binding get() = _binding!!
    private var _talonItemAdapter: TalonItemAdapter? = null
    private val talonItemAdapter get() = _talonItemAdapter
    private val viewModel: KinoGameDetailsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKinoTalonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _talonItemAdapter = TalonItemAdapter() {
            viewModel.talonItemClick(it)
        }
        binding.talonItemRV.layoutManager =
            CustomGridLayoutManager(requireContext(), 10, resources.displayMetrics.widthPixels)
        binding.talonItemRV.adapter = _talonItemAdapter
//        (binding.talonItemRV.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        setupUiStateConsumer()
    }

    private fun setupUiStateConsumer() {
        lifecycleScope.launch {
            activity?.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.kinoGameDetailsUiState.collectLatest { kinoGameUiState ->
                    talonItemAdapter?.submitList(kinoGameUiState.talonList)
                }
            }
        }
    }
}
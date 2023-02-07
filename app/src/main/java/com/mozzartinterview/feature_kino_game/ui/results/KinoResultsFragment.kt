package com.mozzartinterview.feature_kino_game.ui.results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.mozzartinterview.databinding.FragmentKinoResultsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class KinoResultsFragment : Fragment() {
    private var _binding: FragmentKinoResultsBinding? = null
    private val binding get() = _binding!!
    private var _kinoResultsAdapter: KinoResultsAdapter? = null
    private val kinoResultsAdapter get() = _kinoResultsAdapter
    private val viewModel: KinoResultsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKinoResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setup()

        _kinoResultsAdapter = KinoResultsAdapter(resources.displayMetrics.widthPixels)
        binding.resultsItemRV.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.resultsItemRV.adapter = kinoResultsAdapter
        (binding.resultsItemRV.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        setupUiStateConsumer()
    }

    private fun setupUiStateConsumer() {
        lifecycleScope.launch {
            activity?.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.kinoPastGameUiState.collectLatest { kinoPastGameUiState ->
                    if(kinoPastGameUiState.resultList.count() == 0) return@collectLatest
                    _kinoResultsAdapter?.submitList(kinoPastGameUiState.resultList)
                }
            }
        }
    }
}
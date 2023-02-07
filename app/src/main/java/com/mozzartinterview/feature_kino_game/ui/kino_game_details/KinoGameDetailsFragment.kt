package com.mozzartinterview.feature_kino_game.ui.kino_game_details

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mozzartinterview.R
import com.mozzartinterview.core.data.toDrawEndString
import com.mozzartinterview.core.data.toRemaningTimeString
import com.mozzartinterview.databinding.FragmentKinoGameDetailsBinding
import com.mozzartinterview.feature_kino_game.ui.kino_games.KinoGameItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class KinoGameDetailsFragment : Fragment() {
    private var _binding: FragmentKinoGameDetailsBinding? = null
    private val binding get() = _binding!!
    private var viewPager: ViewPager2? = null
    private val viewModel: KinoGameDetailsViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKinoGameDetailsBinding.inflate(inflater, container, false)
        arguments?.getInt(KinoGameConstants.KINO_DRAW_ID_KEY)?.let {
            viewModel.updateKinoDrawId(it)
            viewModel.startRefreshing()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        setupViewPager()
        setupUiStateConsumer()
    }

    private fun setupViewPager() {
        viewPager = binding.viewPager
        if (viewPager?.adapter == null) {
            val adapter =
                activity?.supportFragmentManager?.let { ViewPagerAdapter(it, this.lifecycle) }
            viewPager!!.adapter = adapter
            viewPager!!.offscreenPageLimit = 4

            TabLayoutMediator(binding.tabLayout, viewPager!!) { tab, position ->
                val customView: View? =
                    getSystemService(requireContext(), LayoutInflater::class.java)
                        ?.inflate(R.layout.item_custom_tab, null, false)
                val customIcon = customView?.findViewById<ImageView>(R.id.icon)
                val customText = customView?.findViewById<TextView>(R.id.text)
                customIcon?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        KinoGameConstants.ICON_IDS[position]
                    )
                )
                customText?.text = getString(KinoGameConstants.TITLE_IDS[position])
                tab.customView = customView
                if (position == 0) {
                    customIcon?.imageTintList =
                        ColorStateList.valueOf(requireContext().getColor(R.color.yellow))
                    customText?.setTextColor(requireContext().getColor(R.color.yellow))
                }
            }.attach()

            binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val customIcon = tab.customView?.findViewById<ImageView>(R.id.icon)
                    val customText = tab.customView?.findViewById<TextView>(R.id.text)
                    customIcon?.imageTintList =
                        ColorStateList.valueOf(requireContext().getColor(R.color.yellow))
                    customText?.setTextColor(requireContext().getColor(R.color.yellow))
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    val customIcon = tab.customView?.findViewById<ImageView>(R.id.icon)
                    val customText = tab.customView?.findViewById<TextView>(R.id.text)
                    customIcon?.imageTintList =
                        ColorStateList.valueOf(requireContext().getColor(R.color.white))
                    customText?.setTextColor(requireContext().getColor(R.color.white))
                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }
    }

    private fun setupUiStateConsumer() {
        lifecycleScope.launch {
            activity?.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.kinoGameDetailsUiState.collectLatest { kinoGameUiState ->
                    binding.itemGameDetails.endTime.text =
                        kinoGameUiState.kinoGameItem.drawTime.toDrawEndString()
                    binding.itemGameDetails.leftTime.text =
                        kinoGameUiState.kinoGameItem.remainingTime.toRemaningTimeString()
                    binding.itemGameDetails.numbersCount.text =
                        kinoGameUiState.selectedTalonList.count().toString()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.updateKinoDrawId(0)
        viewPager!!.adapter = null
        viewPager = null
    }
}
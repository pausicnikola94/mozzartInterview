package com.mozzartinterview.feature_kino_game.ui.kino_game_details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mozzartinterview.feature_kino_game.ui.brzzi_kino.BrzziKinoFragment
import com.mozzartinterview.feature_kino_game.ui.live.KinoLiveFragment
import com.mozzartinterview.feature_kino_game.ui.more_games.KinoMoreGamesFragment
import com.mozzartinterview.feature_kino_game.ui.results.KinoResultsFragment
import com.mozzartinterview.feature_kino_game.ui.talon.KinoTalonFragment

private const val NUM_TABS = 5

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return KinoTalonFragment()
            1 -> return KinoMoreGamesFragment()
            2 -> return KinoLiveFragment()
            3 -> return KinoResultsFragment()
        }
        return BrzziKinoFragment()
    }
}
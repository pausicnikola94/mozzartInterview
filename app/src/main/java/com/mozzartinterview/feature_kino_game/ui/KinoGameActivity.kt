package com.mozzartinterview.feature_kino_game.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mozzartinterview.R
import com.mozzartinterview.databinding.ActivityKinoGameBinding
import com.mozzartinterview.feature_kino_game.ui.kino_games.KinoGameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class KinoGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKinoGameBinding
    private val viewModel: KinoGameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKinoGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigation
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph
    }


}
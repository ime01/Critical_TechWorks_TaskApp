package com.example.criticaltechworkstaskapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.criticaltechworkstaskapp.R
import com.example.criticaltechworkstaskapp.databinding.ActivityMainBinding
import com.example.criticaltechworkstaskapp.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_CriticalTechWorksTaskApp)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration.Builder(setOf(R.id.fingerPrintLoginFragment, R.id.topHeadLinesFragment)).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        viewModel.title.observe(this) { sourceNmae ->
            if (!sourceNmae.isNullOrEmpty()) {
                supportActionBar?.title = sourceNmae
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()|| super.onSupportNavigateUp()
    }
}
package com.example.testtaskavito.ui.root

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.testtaskavito.R
import com.example.testtaskavito.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {

    private var _binding: ActivityRootBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.messageFragment -> {
//                    binding.bottomNavigationView.visibility = View.GONE
//                    binding.horizontalLine.visibility = View.GONE
//                }
//
//                else -> {
//                    binding.bottomNavigationView.visibility = View.VISIBLE
//                    binding.horizontalLine.visibility = View.VISIBLE
//                }
//            }
        }

    }
}
package com.example.filmify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.filmify.Fragments.InstructionFragment
import com.example.filmify.Fragments.TopMenuFragment
import com.example.filmify.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val initialFragment = InstructionFragment()
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.mainContainer, initialFragment)
//            .commit()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView, navController)



        // Observe changes to the headerText LiveData in the sharedViewModel
        sharedViewModel.headerText.observe(this) { newHeaderText ->
            // Notify TopMenuFragment about changes in headerText
            val topMenuFragment = navHostFragment.childFragmentManager.fragments
                .find { it is TopMenuFragment } as? TopMenuFragment
            topMenuFragment?.updateHeaderText(newHeaderText)
        }
        setupWithNavController(bottomNavigationView, navController)
    }
}
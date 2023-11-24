package com.example.filmify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigation.NavigationView

class CreateRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_room)

        val noSelectedItem = intent.getBooleanExtra("noSelectedItem", false)

        if (noSelectedItem) {
            // Update your UI to show the navigation menu without any selected items
            // For example, if you have a navigation view, you might clear the checked item
//            val navigationView: NavigationView = findViewById(R.id.bottomNavigationView)
//            navigationView.menu.findItem(R.id.filmsFragment).isChecked = false
//            navigationView.menu.findItem(R.id.roomsFragment).isChecked = false
//            navigationView.menu.findItem(R.id.settingsFragment).isChecked = false
            // ... (clear other menu items as needed)

            // Add your other UI updates here
        }
    }
}
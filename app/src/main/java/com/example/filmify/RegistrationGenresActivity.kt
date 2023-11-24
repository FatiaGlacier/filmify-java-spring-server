package com.example.filmify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmify.GenreButton.GenreAdapter
import com.example.filmify.GenreButton.GenreButtonItem

class RegistrationGenresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_genres)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val itemList = generateItemList() // Replace this with your function to generate items
        val adapter = GenreAdapter(itemList)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun generateItemList(): List<GenreButtonItem> {
        // Create and return a list of GenreItem objects
        return listOf(
            GenreButtonItem("Action"),
            GenreButtonItem("Adventure"),
            GenreButtonItem("Comedy"),
            // Add more items as needed
        )
    }
}
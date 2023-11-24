package com.example.filmify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.filmify.Fragments.TopMenuFragment
import com.google.android.material.button.MaterialButton

class InstructionsActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instructions)
          //Find the TopMenuFragment
//        val topMenuFragment =
//            supportFragmentManager.findFragmentById(R.id.topMenuInstructionFragment) as? TopMenuFragment
//
//        //Access the headerText TextView directly using findViewById and set the desired value
//        val headerText = topMenuFragment.findViewById<TextView>(R.id.header_text)
//        headerText.text = "Інструкція"

        val launchMainActivityButton = findViewById<MaterialButton>(R.id.launchMainActivity)
        launchMainActivityButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
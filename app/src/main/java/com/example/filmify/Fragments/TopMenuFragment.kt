package com.example.filmify.Fragments

import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.filmify.SharedViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.filmify.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TopMenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var headerTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.top_menu, container, false)

        // Find the TextView in your top menu
        headerTextView = view.findViewById(R.id.header_text)

        // Observe changes to the headerText LiveData
        sharedViewModel.headerText.observe(viewLifecycleOwner) { newHeaderText ->
            Log.d("TopMenuFragment", "Observe block executed for headerText")
            Log.d("TopMenuFragment", "New headerText: $newHeaderText")
            headerTextView.text = newHeaderText
        }
        return view
    }

    fun updateHeaderText(newHeaderText: String) {
        headerTextView.text = newHeaderText
    }
}






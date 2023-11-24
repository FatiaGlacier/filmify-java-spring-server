package com.example.filmify.Fragments


import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Bundle
//import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
//import com.bluejamesbond.text.DocumentView
import com.example.filmify.R




private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InstructionFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instruction, container, false)
//        val textView: TextView = view.findViewById(R.id.myTextView)
//        textView.justificationMode = ConstraintSet.Layout.JUSTIFICATION_MODE_INTER_WORD
//        textView.text = "Your justified text goes here"
//
//        textView.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
//        default is JUSTIFICATION_MODE_NONE
//
//        val txtViewEx: TextViewEx = findViewById(R.id.textViewEx) as TextViewEx
//        txtViewEx.setText("Insert your content here", true)
//        val documentView: DocumentView = view.findViewById(R.id.belowTextView)
//        documentView.text = "Your text goes here"

//        return view
    }
}
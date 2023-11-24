package com.example.filmify.Popups

import UnderlineSpaceFilter
import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.TextWatcher
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.filmify.R
import com.google.android.material.button.MaterialButton

class JoiningRoomPopup(private val fragment: Fragment) {
    private val popupWindow: PopupWindow

    init {
        val view = LayoutInflater.from(fragment.requireContext()).inflate(R.layout.popup_joining_room, null)
        popupWindow = PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true)
        popupWindow.setBackgroundDrawable(fragment.requireContext().getDrawable(R.drawable.blur_background)) // Set the custom background here

        // Assuming you have a dismiss button in your popup layout
        val dismissButton: MaterialButton = view.findViewById(R.id.joinRoomButton)
        dismissButton.setOnClickListener {
            dismissPopup()
        }
//        val codeEditText = view.findViewById<EditText>(R.id.codeEditText);
//        codeEditText.setText("_ _ _ - _ _ _")
//        codeEditText.filters = arrayOf(UnderlineSpaceFilter())

        // Set the custom InputFilter to the EditText


        //TODO: to correct the code so the popup would dismiss when there is touch outside of popup background
//        popupWindow.isOutsideTouchable = true
//        view.setOnTouchListener { _, event ->
//            if (event.action == MotionEvent.ACTION_DOWN) {
//                val location = IntArray(2)
//                view.getLocationOnScreen(location)
//
//                // Check if the touch event is outside the popup's bounds
//                if (event.rawX < location[0] || event.rawX > location[0] + view.width ||
//                    event.rawY < location[1] || event.rawY > location[1] + view.height
//                ) {
//                    dismissPopup()
//                    true // consume the touch event
//                } else {
//                    false // allow the touch event to be handled by the popup
//                }
//            } else {
//                false // allow other touch events to be handled by the popup
//            }
//        }

    }

    fun showPopup(anchorView: View) {
        // Customize popup content or behavior as needed
        //val popupText: TextView = popupWindow.contentView.findViewById(R.id.popupText)
        //popupText.text = "Joining room from ${fragment.javaClass.simpleName}"

        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0)
    }

    fun dismissPopup() {
        popupWindow.dismiss()
    }
}
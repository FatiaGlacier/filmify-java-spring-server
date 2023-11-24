package com.example.filmify.RoomInfo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.example.filmify.R

class RoomInfoView(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {
    init {
        // Call the super constructor
        // super(context)
        // Inflate the base layout
        LayoutInflater.from(context).inflate(R.layout.room_info_layout, this, true)
        // Additional setup or customization can be done here
    }

    fun setTextForLayout(code: Int, roomName: String, usersList: String) {
        val fixedText = "КОД:"
        val codeText = StringBuilder(code.toString());
        codeText.insert(codeText.length/2, "-")
        findViewById<TextView>(R.id.codeTextView).text = "$fixedText\n$codeText"
        findViewById<TextView>(R.id.roomNameTextView).text = roomName;
        findViewById<TextView>(R.id.userListTextView).text = usersList;
    }
}
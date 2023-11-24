package com.example.filmify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmify.RoomInfo.RoomInfoAdapter
import com.example.filmify.RoomInfo.RoomInfoData

class InsertingCodeInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_inserting_code_info)
//        val container = findViewById<FrameLayout>(R.id.rooomsInfoContainer)
//
//        // Create and add objects dynamically
//        for (i in 1..2) {
//            val roomInfoView = RoomInfoView(this)
//            roomInfoView.setTextForLayout(123456, "Фільм на вечір 11", "Miss_popularnost1, kr4m44, rodnylka1221, vasyl_shpack_9 " )
//            // Set text for other sections as needed
//            container.addView(roomInfoView)
//        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inserting_code_info)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Create a list of RoomInfoData using a loop
        val roomInfoList = mutableListOf<RoomInfoData>()

        for (i in 1..10) {
            val roomInfoData = RoomInfoData(
                123456 + i,  // Example: Increment the code for each RoomInfoData
                "Фільм на вечір $i",  // Example: Different room names for each RoomInfoData
                "User${i}_1, User${i}_2, User${i}_3"  // Example: Different user lists for each RoomInfoData
            )

            roomInfoList.add(roomInfoData)
        }

        // Set up RecyclerView with RoomInfoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RoomInfoAdapter(roomInfoList)

    }
}
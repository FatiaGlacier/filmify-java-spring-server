package com.example.filmify.RoomInfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.filmify.R

class RoomInfoAdapter(private val roomInfoList: List<RoomInfoData>) :
    RecyclerView.Adapter<RoomInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_room_info, parent, false)
        return RoomInfoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RoomInfoViewHolder, position: Int) {
        val currentItem = roomInfoList[position]
        holder.roomInfoView.setTextForLayout(currentItem.code, currentItem.roomName, currentItem.usersList)
    }

    override fun getItemCount(): Int {
        return roomInfoList.size
    }
}

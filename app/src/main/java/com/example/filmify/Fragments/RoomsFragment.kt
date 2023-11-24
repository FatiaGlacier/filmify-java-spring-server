package com.example.filmify.Fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmify.CreateRoomActivity
import com.example.filmify.Popups.JoiningRoomPopup
import com.example.filmify.R
import com.example.filmify.RoomInfo.RoomInfoAdapter
import com.example.filmify.RoomInfo.RoomInfoData
import com.example.filmify.SharedViewModel
import com.google.android.material.button.MaterialButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoomsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var joiningRoomPopup: JoiningRoomPopup

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
        val view = inflater.inflate(R.layout.fragment_rooms, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

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
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = RoomInfoAdapter(roomInfoList)

        val imageButton: ImageButton = view.findViewById(R.id.createRoom)
        imageButton.setOnClickListener(::onImageButtonClick)

        joiningRoomPopup = JoiningRoomPopup(this)
        val joinRoomButton: MaterialButton = view.findViewById(R.id.joinRoomButton)
        joinRoomButton.setOnClickListener {
            joiningRoomPopup.showPopup(it)
        }
        return view
    }

    private fun onImageButtonClick(view: View) {
        // Handle the click event
        // Add your custom logic here
        Toast.makeText(requireContext(), "перемога настала\nа кімнати закінчились", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), CreateRoomActivity::class.java)
        intent.putExtra("noSelectedItem", true)
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.headerText.value = "Кімнати"
    }
}
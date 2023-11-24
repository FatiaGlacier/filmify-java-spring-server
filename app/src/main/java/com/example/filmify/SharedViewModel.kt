package com.example.filmify

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    // Define MutableLiveData for each piece of shared data
    val headerText = MutableLiveData<String>().apply { value = "Initial Value" }
}
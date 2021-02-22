package com.mahidhar.superapp.ui.homefragment.sponsors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val urls = listOf<String>( "https://images.pexels.com/photos/1092730/pexels-photo-1092730.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=150",
    "https://images.pexels.com/photos/842519/pexels-photo-842519.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=200")
    val text: LiveData<String> = Transformations.map(_index) {
        urls[it]
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}
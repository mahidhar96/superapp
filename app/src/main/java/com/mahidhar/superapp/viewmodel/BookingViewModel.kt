package com.mahidhar.superapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahidhar.superapp.model.BookingItem
import com.mahidhar.superapp.service.BookingAPI
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class BookingViewModel: ViewModel() {
    private lateinit var bookingItemLiveList: MutableLiveData<List<BookingItem>>

    init{
        bookingItemLiveList = MutableLiveData()
//        populateList()
    }

    public fun getBookingItemList():MutableLiveData<List<BookingItem>>{
        viewModelScope.launch{
            try{
                val bookingItemResult = BookingAPI.retrofitService.getBookingItems()
                bookingItemResult.enqueue(object: Callback<List<BookingItem>> {
                    override fun onResponse(
                        call: Call<List<BookingItem>>?,
                        response: Response<List<BookingItem>>?
                    ) {
                        Log.i("BookingViewModel","Received: "+response.toString())
                        bookingItemLiveList.postValue(response!!.body())
                    }

                    override fun onFailure(call: Call<List<BookingItem>>?, t: Throwable?) {
                        Log.e("BookingViewModel","Failure Call: "+t.toString())
                    }

                })

            }catch (e: Exception){
                Log.e("BookingViewModel","Error getting bookingItemLiveList "+e.toString())
            }
        }

        return bookingItemLiveList
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Booking View Model", "Cleared")
    }
}
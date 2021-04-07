package com.mahidhar.superapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahidhar.superapp.model.MicroApp
import com.mahidhar.superapp.service.FeaturedAPI
//import com.mahidhar.superapp.service.FeaturedAPI
//import com.mahidhar.superapp.service.FeaturedAPIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class FeaturedViewModel:ViewModel() {

    private lateinit var microAppLiveList: MutableLiveData<List<MicroApp>>

    init{
        microAppLiveList = MutableLiveData()
//        populateList()
    }

    public fun getMicroAppList():MutableLiveData<List<MicroApp>>{
        viewModelScope.launch{
            try{
                val microAppResult = FeaturedAPI.retrofitService.getMicroAppList()
                microAppResult.enqueue(object: Callback<List<MicroApp>>{
                    override fun onResponse(
                        call: Call<List<MicroApp>>?,
                        response: Response<List<MicroApp>>?
                    ) {
                        Log.i("FeaturedViewModel","Received: "+response.toString())
                        microAppLiveList!!.postValue(response!!.body())
                    }

                    override fun onFailure(call: Call<List<MicroApp>>?, t: Throwable?) {
                        Log.e("FeaturedViewModel","Failure Call: "+t.toString())
                    }

                })

            }catch (e:Exception){
                Log.e("FeaturedViewModel","Error getting microapplist "+e.toString())
            }
        }

        return microAppLiveList
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Featured View Model", "Cleared")
    }

//    private fun populateList(){
//        val microAppList:List<MicroApp> = listOf(
//            MicroApp(0,"Pay Bills","Pay Bills", "web","receipt_black","https://www.infosys.com/"),
//            MicroApp(1,"Payment App","Scan & Pay", "web","payments_black","https://www.infosys.com/"),
//            MicroApp(2,"Food Delivery","Order Food", "web","dining_black","https://www.infosys.com/"),
//            MicroApp(3,"Bus Tickets","Book Bus Tickets", "instant","bus_black","https://www.infosys.com/"),
//            MicroApp(4,"Stuff Delivery","Send Stuff", "web","delivery_black","https://www.infosys.com/"),
//            MicroApp(5,"Flight Tickets","Book FLight Tickets", "instant","flight_black","https://www.infosys.com/"),
//            MicroApp(6,"Subscriptions","Subscribe and Pay", "web","subscriptions_black","https://www.infosys.com/"),
//            MicroApp(7,"Take Surveys","Take Surveys", "instant","qewewe","https://www.infosys.com/")
//        )
//
//        microAppLiveList!!.postValue(microAppList)
//    }
}
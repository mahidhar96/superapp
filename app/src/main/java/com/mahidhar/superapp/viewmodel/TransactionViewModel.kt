package com.mahidhar.superapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahidhar.superapp.model.Transaction
import com.mahidhar.superapp.service.TransactionAPI
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class TransactionViewModel: ViewModel() {
    private lateinit var transactionLiveList: MutableLiveData<List<Transaction>>

    init{
        transactionLiveList = MutableLiveData()
//        populateList()
    }

    public fun getMicroAppList():MutableLiveData<List<Transaction>>{
        viewModelScope.launch{
            try{
                val transactionResult = TransactionAPI.retrofitService.getMicroAppList()
                transactionResult.enqueue(object: Callback<List<Transaction>> {
                    override fun onResponse(
                        call: Call<List<Transaction>>?,
                        response: Response<List<Transaction>>?
                    ) {
                        Log.i("TransactionViewModel","Received: "+response.toString())
                        transactionLiveList!!.postValue(response!!.body())
                    }

                    override fun onFailure(call: Call<List<Transaction>>?, t: Throwable?) {
                        Log.e("TransactionViewModel","Failure Call: "+t.toString())
                    }

                })

            }catch (e: Exception){
                Log.e("TransactionViewModel","Error getting microapplist "+e.toString())
            }
        }

        return transactionLiveList
    }
}
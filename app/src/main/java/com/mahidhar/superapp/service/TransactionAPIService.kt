package com.mahidhar.superapp.service

import com.mahidhar.superapp.model.Transaction
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private val BASE_URL:String = "http://127.0.0.1:7000/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit  .Builder()
    .addConverterFactory(MoshiConverterFactory .create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface TransactionAPIService {
    @GET("transactions")
    fun getMicroAppList(): Call<List<Transaction>>
}

object TransactionAPI {
    val retrofitService : TransactionAPIService by lazy { retrofit.create(TransactionAPIService::class.java) }
}

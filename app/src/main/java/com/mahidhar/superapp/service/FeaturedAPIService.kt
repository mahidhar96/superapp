package com.mahidhar.superapp.service

import com.mahidhar.superapp.model.MicroApp
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

import java.util.*

private const val BASE_URL = "http://192.168.0.4:8000/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit  .Builder()
    .addConverterFactory(MoshiConverterFactory .create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface FeaturedAPIService {
    @GET("data.json")
    fun getMicroAppList():Call<List<MicroApp>>
}

object FeaturedAPI {
    val retrofitService : FeaturedAPIService by lazy { retrofit.create(FeaturedAPIService::class.java) }
}
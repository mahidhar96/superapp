package com.mahidhar.superapp.service

import com.mahidhar.superapp.Constants
import com.mahidhar.superapp.model.BookingItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val BASE_URL: String = Constants.base_url

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BookingAPIService {
    @GET("bookingitems")
    fun getBookingItems(): Call<List<BookingItem>>
}

object BookingAPI {
    val retrofitService: BookingAPIService by lazy { retrofit.create(BookingAPIService::class.java) }
}
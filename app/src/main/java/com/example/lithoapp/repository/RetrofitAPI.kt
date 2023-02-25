package com.example.lithoapp.repository

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("content/dam/insurance-mall/homepage/homepage-bfl/android/android_homepage_bfl.json")
    fun getData(): Call<ApiResponse?>?
}
package com.example.pinterest_clone_with_retrofit_inkotlin.activity.network

import com.example.pinterest_clone_with_retrofit_inkotlin.activity.network.service.PhotosService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHttp {
    val IS_TESTER = true
    val SERVER_DEVELOPMENT = "https://api.unsplash.com/"
    val SERVER_PRODUCTION = "https://api.unsplash.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(server())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun server(): String{
        if (IS_TESTER) return SERVER_DEVELOPMENT
        return SERVER_PRODUCTION
    }

    val photosService: PhotosService = retrofit.create(PhotosService::class.java)

    fun <T> createService(service: Class<T>): T{
        return retrofit.create(service)
    }
}
package com.artemiod.challengeandroidssr.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MyRetrofit {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
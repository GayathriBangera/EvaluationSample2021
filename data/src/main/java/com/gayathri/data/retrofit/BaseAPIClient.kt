package com.gayathri.data.retrofit

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseAPIClient {

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

    fun buildRetrofit(): NewsAPIService =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(NewsAPIService::class.java)

}

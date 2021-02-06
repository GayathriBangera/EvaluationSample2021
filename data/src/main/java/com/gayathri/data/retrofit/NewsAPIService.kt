package com.gayathri.data.retrofit

import com.gayathri.domain.models.response.NewsResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    companion object {
        const val API_KEY = "apiKey=1eae65a4ac8d4ce6985ba23079a9223a"
    }

    @GET("top-headlines?sources=bbc-news&pageSize=1&page=1&$API_KEY")
    fun getTopNews(): Flowable<NewsResponse>

    @GET("everything?q=bitcoin&$API_KEY")
    fun getPopularNewsList(
        @Query("pageSize") pageSize: Int = 10
    ): Flowable<NewsResponse>

}

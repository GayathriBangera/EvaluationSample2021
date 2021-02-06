package com.gayathri.data.retrofit

import com.gayathri.domain.models.response.NewsResponse
import io.reactivex.Flowable

class NewsAPIClientImp : NewsAPIClient, BaseAPIClient() {

    private val retrofit: NewsAPIService = buildRetrofit()

    override fun getTopNews(): Flowable<NewsResponse> = retrofit.getTopNews()
    override fun getPopularNewsList(pageSize: Int): Flowable<NewsResponse> =
        retrofit.getPopularNewsList(pageSize)

}

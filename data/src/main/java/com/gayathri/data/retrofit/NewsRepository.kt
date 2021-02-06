package com.gayathri.data.retrofit

import com.gayathri.domain.models.response.NewsResponse
import io.reactivex.Flowable

class NewsRepository(private val newsAPIClientImp: NewsAPIClientImp) {
    fun getTopNews(): Flowable<NewsResponse> = newsAPIClientImp.getTopNews()
    fun getPopularNewsList(pageSize: Int): Flowable<NewsResponse> =
        newsAPIClientImp.getPopularNewsList(pageSize)
}

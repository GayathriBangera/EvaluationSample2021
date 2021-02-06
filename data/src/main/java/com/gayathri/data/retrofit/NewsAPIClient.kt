package com.gayathri.data.retrofit

import com.gayathri.domain.models.response.NewsResponse
import io.reactivex.Flowable

interface NewsAPIClient {
    fun getTopNews(): Flowable<NewsResponse>
    fun getPopularNewsList(pageSize: Int): Flowable<NewsResponse>
}

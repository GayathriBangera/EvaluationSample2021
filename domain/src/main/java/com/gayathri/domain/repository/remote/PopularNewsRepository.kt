package com.gayathri.domain.repository.remote

import com.gayathri.domain.models.response.NewsResponse
import io.reactivex.Flowable

interface PopularNewsRepository {

    fun getPopularNewsList(pageSize: Int): Flowable<NewsResponse>
}

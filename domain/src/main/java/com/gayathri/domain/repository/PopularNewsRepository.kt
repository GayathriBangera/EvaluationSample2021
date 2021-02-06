package com.gayathri.domain.repository

import com.gayathri.domain.models.response.NewsResponse
import io.reactivex.Flowable

interface PopularNewsRepository {

    fun getPopularNewsList(pageSize: Int): Flowable<NewsResponse>
}

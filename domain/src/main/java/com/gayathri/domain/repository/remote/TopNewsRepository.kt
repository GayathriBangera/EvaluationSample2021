package com.gayathri.domain.repository.remote

import com.gayathri.domain.models.response.NewsResponse
import io.reactivex.Flowable

interface TopNewsRepository {

    fun getTopNews(): Flowable<NewsResponse>
}

package com.gayathri.domain.repository

import com.gayathri.domain.models.response.NewsResponse
import io.reactivex.Flowable

interface TopNewsRepository {
    fun getTopNews(): Flowable<NewsResponse>
}

package com.gayathri.data.repositories

import com.gayathri.data.retrofit.NewsRepository
import com.gayathri.domain.models.response.NewsResponse
import com.gayathri.domain.repository.TopNewsRepository
import io.reactivex.Flowable

class TopTopNewsRepositoryImp(
    private val newsRepository: NewsRepository
) : TopNewsRepository {

    override fun getTopNews(): Flowable<NewsResponse> = newsRepository.getTopNews()
}

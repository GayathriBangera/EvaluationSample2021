package com.gayathri.data.repositories.remote

import com.gayathri.data.retrofit.NewsRepository
import com.gayathri.domain.models.response.NewsResponse
import com.gayathri.domain.repository.remote.PopularNewsRepository
import io.reactivex.Flowable

class PopularNewsRepositoryImp(
    private val newsRepository: NewsRepository
) : PopularNewsRepository {

    override fun getPopularNewsList(pageSize: Int): Flowable<NewsResponse> =
        newsRepository.getPopularNewsList(pageSize)
}


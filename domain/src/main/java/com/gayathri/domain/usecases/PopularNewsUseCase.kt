package com.gayathri.domain.usecases

import com.gayathri.domain.models.response.NewsResponse
import com.gayathri.domain.repository.PopularNewsRepository
import io.reactivex.Flowable

class PopularNewsUseCase(
    private val popularNewsRepository: PopularNewsRepository
) {

    fun getPopularNewsList(pageSize: Int): Flowable<NewsResponse> =
        popularNewsRepository.getPopularNewsList(pageSize)
}

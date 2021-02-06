package com.gayathri.domain.usecases

import com.gayathri.domain.models.response.NewsResponse
import com.gayathri.domain.repository.TopNewsRepository
import io.reactivex.Flowable

class TopNewsUseCase(private val topNewsRepository: TopNewsRepository) {
    fun getTopNews(): Flowable<NewsResponse> = topNewsRepository.getTopNews()
}

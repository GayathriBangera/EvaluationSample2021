package com.gayathri.domain.usecases

import com.gayathri.domain.models.room.NewsArticleRoom
import com.gayathri.domain.repository.room.PopularNewsRoomRepository
import io.reactivex.Flowable

class PopularNewsRoomUseCase(
    private val popularNewsRepository: PopularNewsRoomRepository
) {

    fun insertPopularNews(newsArticles: List<NewsArticleRoom>) = popularNewsRepository.insertPopularNews(newsArticles)

    fun getPopularNewsList(): Flowable<List<NewsArticleRoom>> =
        popularNewsRepository.getPopularNews()
}
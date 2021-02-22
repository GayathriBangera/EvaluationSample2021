package com.gayathri.data.repositories.room

import com.gayathri.data.room.NewsRoomRepository
import com.gayathri.domain.models.room.NewsArticleRoom
import com.gayathri.domain.repository.room.PopularNewsRoomRepository
import io.reactivex.Flowable

class PopularNewsRoomRepositoryImp(private val newsRoomRepository: NewsRoomRepository) :
    PopularNewsRoomRepository {
    override fun insertPopularNews(newsArticles: List<NewsArticleRoom>) =
        newsRoomRepository.insertPopularNews(newsArticles)

    override fun getPopularNews(): Flowable<List<NewsArticleRoom>> =
        newsRoomRepository.getPopularNews()
}
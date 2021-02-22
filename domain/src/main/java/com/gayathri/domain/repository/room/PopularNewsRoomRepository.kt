package com.gayathri.domain.repository.room

import com.gayathri.domain.models.room.NewsArticleRoom
import io.reactivex.Flowable

interface PopularNewsRoomRepository {
    fun insertPopularNews(newsArticles: List<NewsArticleRoom>)
    fun getPopularNews(): Flowable<List<NewsArticleRoom>>
}
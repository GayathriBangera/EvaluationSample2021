package com.gayathri.domain.models.room

import androidx.room.*

@Dao
interface PopularNewsDao {

    @Query("SELECT * FROM news_article_table")
    fun getPopularNews(): List<NewsArticleRoom>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(bookmark: List<NewsArticleRoom>)

    @Update
    fun update(bookmark: Bookmark)
}

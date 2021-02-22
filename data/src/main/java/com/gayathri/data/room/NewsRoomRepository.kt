package com.gayathri.data.room

import android.content.Context
import com.gayathri.data.room.NewsRoomDatabase.Companion.getDatabase
import com.gayathri.domain.models.room.Bookmark
import com.gayathri.domain.models.room.NewsArticleRoom
import io.reactivex.Flowable

class NewsRoomRepository(
    context: Context
) {

    private val roomDatabaseInstance = getDatabase(context)

    fun getBookmarkByTitle(title: String): Flowable<Bookmark> =
        Flowable.fromCallable {
            roomDatabaseInstance.bookMarkDao().getBookmarkByTitle(title)
        }

    fun insert(bookmark: Bookmark) = roomDatabaseInstance.bookMarkDao().insert(bookmark)


    fun getBookmarks(): Flowable<List<Bookmark>> = Flowable.fromCallable {
        roomDatabaseInstance.bookMarkDao().getBookmarks()
    }

    fun getPopularNews(): Flowable<List<NewsArticleRoom>> = Flowable.fromCallable {
        roomDatabaseInstance.popularNewsDao().getPopularNews()
    }

    fun insertPopularNews(newsArticle: List<NewsArticleRoom>) =
        roomDatabaseInstance.popularNewsDao().insertAll(newsArticle)

}
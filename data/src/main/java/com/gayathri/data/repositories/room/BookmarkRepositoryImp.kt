package com.gayathri.data.repositories.room

import com.gayathri.data.room.NewsRoomRepository
import com.gayathri.domain.models.room.Bookmark
import com.gayathri.domain.repository.room.BookmarkRepository
import io.reactivex.Flowable

class BookmarkRepositoryImp(private val newsRoomRepository: NewsRoomRepository) :
    BookmarkRepository {

    override fun insertBookmark(bookmark: Bookmark) = newsRoomRepository.insert(bookmark)

    override fun getBookmarkByTitle(title: String): Flowable<Bookmark> =
        newsRoomRepository.getBookmarkByTitle(title)

    override fun geBookmarks() = newsRoomRepository.getBookmarks()
}
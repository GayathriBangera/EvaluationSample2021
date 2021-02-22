package com.gayathri.domain.usecases

import com.gayathri.domain.models.room.Bookmark
import com.gayathri.domain.repository.room.BookmarkRepository
import io.reactivex.Flowable

class BookmarkUseCase(
    private val bookmarkRepository: BookmarkRepository
) {

    fun getBookMark(title: String): Flowable<Bookmark> =
        bookmarkRepository.getBookmarkByTitle(title)

    fun getBookMarks(): Flowable<List<Bookmark>> = bookmarkRepository.geBookmarks()

    fun insertBookMark(bookmark: Bookmark) = bookmarkRepository.insertBookmark(bookmark)
}

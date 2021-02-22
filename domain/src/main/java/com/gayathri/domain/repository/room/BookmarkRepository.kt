package com.gayathri.domain.repository.room

import com.gayathri.domain.models.room.Bookmark
import io.reactivex.Flowable

interface BookmarkRepository {
    fun insertBookmark(bookmark: Bookmark)
    fun getBookmarkByTitle(title: String): Flowable<Bookmark>
    fun geBookmarks(): Flowable<List<Bookmark>>
}
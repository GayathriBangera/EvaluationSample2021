package com.gayathri.domain.models.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark_table WHERE title = :title")
    fun getBookmarkByTitle(title: String): Bookmark

    @Query("SELECT * FROM bookmark_table")
    fun getBookmarks(): List<Bookmark>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(bookmark: Bookmark)
}

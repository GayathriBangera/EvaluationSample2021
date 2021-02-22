package com.gayathri.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gayathri.domain.models.room.Bookmark
import com.gayathri.domain.models.room.BookmarkDao
import com.gayathri.domain.models.room.NewsArticleRoom
import com.gayathri.domain.models.room.PopularNewsDao

@Database(entities = [Bookmark::class, NewsArticleRoom::class], version = 1, exportSchema = false)
abstract class NewsRoomDatabase : RoomDatabase() {

    abstract fun bookMarkDao(): BookmarkDao
    abstract fun popularNewsDao(): PopularNewsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NewsRoomDatabase? = null
        fun getDatabase(context: Context): NewsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NewsRoomDatabase::class.java,
                    "news_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

package com.gayathri.evaluationsample.di

import com.gayathri.data.repositories.remote.PopularNewsRepositoryImp
import com.gayathri.data.repositories.remote.TopTopNewsRepositoryImp
import com.gayathri.data.repositories.room.BookmarkRepositoryImp
import com.gayathri.data.repositories.room.PopularNewsRoomRepositoryImp
import com.gayathri.data.retrofit.NewsAPIClientImp
import com.gayathri.data.retrofit.NewsRepository
import com.gayathri.data.room.NewsRoomDatabase
import com.gayathri.data.room.NewsRoomRepository
import com.gayathri.domain.repository.remote.PopularNewsRepository
import com.gayathri.domain.repository.remote.TopNewsRepository
import com.gayathri.domain.repository.room.BookmarkRepository
import com.gayathri.domain.repository.room.PopularNewsRoomRepository
import org.koin.dsl.module

val dataModule = module {

    //remote repo
    single { NewsRepository(get()) }
    single { NewsAPIClientImp() }

    single<TopNewsRepository> {
        TopTopNewsRepositoryImp(
            get()
        )
    }
    single<PopularNewsRepository> {
        PopularNewsRepositoryImp(
            get()
        )
    }

    //room repo
    single {
        NewsRoomRepository(get())
    }

    single<BookmarkRepository> {
        BookmarkRepositoryImp(get())
    }

    single<PopularNewsRoomRepository> {
        PopularNewsRoomRepositoryImp(get())
    }

}

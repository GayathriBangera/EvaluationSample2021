package com.gayathri.evaluationsample.di

import com.gayathri.domain.usecases.BookmarkUseCase
import com.gayathri.domain.usecases.PopularNewsRoomUseCase
import com.gayathri.domain.usecases.TopNewsUseCase
import com.gayathri.domain.usecases.PopularNewsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { TopNewsUseCase(get()) }
    single { PopularNewsUseCase(get()) }

    single { BookmarkUseCase(get()) }
    single { PopularNewsRoomUseCase(get()) }
}

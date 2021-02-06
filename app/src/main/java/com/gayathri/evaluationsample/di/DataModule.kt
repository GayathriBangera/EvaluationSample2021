package com.gayathri.evaluationsample.di

import com.gayathri.data.repositories.TopTopNewsRepositoryImp
import com.gayathri.data.repositories.PopularNewsRepositoryImp
import com.gayathri.data.retrofit.NewsAPIClientImp
import com.gayathri.data.retrofit.NewsRepository
import com.gayathri.domain.repository.TopNewsRepository
import com.gayathri.domain.repository.PopularNewsRepository
import org.koin.dsl.module

val dataModule = module {
    single { NewsRepository(get()) }
    single { NewsAPIClientImp() }
    single<TopNewsRepository> { TopTopNewsRepositoryImp(get()) }
    single<PopularNewsRepository> { PopularNewsRepositoryImp(get()) }
}

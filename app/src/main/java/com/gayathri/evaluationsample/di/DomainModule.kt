package com.gayathri.evaluationsample.di

import com.gayathri.domain.usecases.TopNewsUseCase
import com.gayathri.domain.usecases.PopularNewsUseCase
import org.koin.dsl.module

val domainModule = module {
    single { TopNewsUseCase(get()) }
    single { PopularNewsUseCase(get()) }
}

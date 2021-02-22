package com.gayathri.evaluationsample.di

import com.gayathri.evaluationsample.presentation.viewmodels.TopNewsViewModel
import com.gayathri.evaluationsample.presentation.viewmodels.PopularNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        TopNewsViewModel(
            get()
        )
    }
    viewModel {
        PopularNewsViewModel(get(), get(), get(),get())
    }
}

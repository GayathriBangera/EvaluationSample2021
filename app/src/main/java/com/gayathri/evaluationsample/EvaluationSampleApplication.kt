package com.gayathri.evaluationsample

import android.app.Application
import com.gayathri.evaluationsample.di.DI.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EvaluationSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EvaluationSampleApplication)
            modules(appModules)
        }
    }
}

package com.test.newsapp

import android.app.Application
import com.test.newsapp.data.di.dataModule
import com.test.newsapp.di.presentationModules
import com.test.newsapp.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

open class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NewsApplication)

            loadKoinModules(domainModule + dataModule + presentationModules)
        }
    }
}

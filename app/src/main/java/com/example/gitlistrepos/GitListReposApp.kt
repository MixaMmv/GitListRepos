package com.example.gitlistrepos

import android.app.Application
import com.example.gitlistrepos.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class GitListReposApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@GitListReposApp)
            modules(appModule)
        }

    }


}
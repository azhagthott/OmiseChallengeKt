package dev.fb.android.tamboon

import android.app.Application
import dev.fb.android.tamboon.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TamboonApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TamboonApp)
            modules(appModule)
        }
    }
}
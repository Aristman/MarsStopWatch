package ru.marslab.marsstopwatch

import android.app.Application
import org.koin.core.context.startKoin
import ru.marslab.marsstopwatch.di.dataModules
import ru.marslab.marsstopwatch.di.viewModelModules

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(dataModules, viewModelModules)
        }
    }
}
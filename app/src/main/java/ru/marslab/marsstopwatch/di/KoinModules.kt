package ru.marslab.marsstopwatch.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.marslab.marsstopwatch.data.TimestampProviderImpl
import ru.marslab.marsstopwatch.domain.TimestampProvider
import ru.marslab.marsstopwatch.presentation.MainViewModel

val dataModules = module {
    single<TimestampProvider> { TimestampProviderImpl() }
}

val viewModelModules = module {
    viewModel { MainViewModel(get()) }
}
package ru.marslab.marsstopwatch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ru.marslab.marsstopwatch.domain.ElapsedTimeCalculator
import ru.marslab.marsstopwatch.domain.StopwatchListOrchestrator
import ru.marslab.marsstopwatch.domain.StopwatchStateCalculator
import ru.marslab.marsstopwatch.domain.TimestampProvider

class MainViewModel(
    timestampProvider: TimestampProvider
) : ViewModel() {
    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)
            ),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        viewModelScope
    )

    val ticker: LiveData<String> = stopwatchListOrchestrator.ticker.asLiveData()

    fun timerStart() {
        stopwatchListOrchestrator.start()
    }

    fun timerPause() {
        stopwatchListOrchestrator.pause()
    }

    fun timerStop() {
        stopwatchListOrchestrator.stop()
    }
}
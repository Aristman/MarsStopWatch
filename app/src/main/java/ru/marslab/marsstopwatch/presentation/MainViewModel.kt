package ru.marslab.marsstopwatch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.marslab.marsstopwatch.domain.ElapsedTimeCalculator
import ru.marslab.marsstopwatch.domain.StopwatchListOrchestrator
import ru.marslab.marsstopwatch.domain.StopwatchStateCalculator
import ru.marslab.marsstopwatch.domain.TimestampProvider

class MainViewModel(
    timestampProvider: TimestampProvider
) : ViewModel() {
    private val stopwatchListOrchestrator: List<StopwatchListOrchestrator> =
        (0..1).map {
            StopwatchListOrchestrator(
                StopwatchStateHolder(
                    StopwatchStateCalculator(
                        timestampProvider,
                        ElapsedTimeCalculator(timestampProvider)
                    ),
                    ElapsedTimeCalculator(timestampProvider),
                    TimestampMillisecondsFormatter()
                ),
                CoroutineScope(Dispatchers.Main + SupervisorJob())
            )
        }

    val ticker: List<LiveData<String>> = stopwatchListOrchestrator.map { it.ticker.asLiveData() }

    fun timerStart(timer: Int) {
        stopwatchListOrchestrator[timer - 1].start()
    }

    fun timerPause(timer: Int) {
        stopwatchListOrchestrator[timer - 1].pause()
    }

    fun timerStop(timer: Int) {
        stopwatchListOrchestrator[timer - 1].stop()
    }
}
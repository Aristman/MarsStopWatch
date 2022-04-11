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

private const val COUNT_TIMERS = 2

class MainViewModel(
    timestampProvider: TimestampProvider
) : ViewModel() {
    private val stopwatchListOrchestrator: List<StopwatchListOrchestrator> =
        mutableListOf<StopwatchListOrchestrator>().apply {
            repeat(COUNT_TIMERS) {
                add(
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
                )
            }
        }.toList()

    val ticker: List<LiveData<String>> = stopwatchListOrchestrator.map { it.ticker.asLiveData() }

    fun timerStart(timer: Int) {
        stopwatchListOrchestrator[timer].start()
    }

    fun timerPause(timer: Int) {
        stopwatchListOrchestrator[timer].pause()
    }

    fun timerStop(timer: Int) {
        stopwatchListOrchestrator[timer].stop()
    }
}
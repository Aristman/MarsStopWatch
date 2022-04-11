package ru.marslab.marsstopwatch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import ru.marslab.marsstopwatch.databinding.ActivityMainBinding

private const val TIMER_ONE = 0
private const val TIMER_TWO = 1

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        mainViewModel.ticker[TIMER_ONE].observe(this) {
            binding.textTimeOne.text = it
        }
        mainViewModel.ticker[TIMER_TWO].observe(this) {
            binding.textTimeTwo.text = it
        }
    }

    private fun initListeners() {
        binding.run {
            buttonStartOne.setOnClickListener {
                mainViewModel.timerStart(TIMER_ONE)
            }
            buttonPauseOne.setOnClickListener {
                mainViewModel.timerPause(TIMER_ONE)
            }
            buttonStopOne.setOnClickListener {
                mainViewModel.timerStop(TIMER_ONE)
            }
            buttonStartTwo.setOnClickListener {
                mainViewModel.timerStart(TIMER_TWO)
            }
            buttonPauseTwo.setOnClickListener {
                mainViewModel.timerPause(TIMER_TWO)
            }
            buttonStopTwo.setOnClickListener {
                mainViewModel.timerStop(TIMER_TWO)
            }
        }
    }
}


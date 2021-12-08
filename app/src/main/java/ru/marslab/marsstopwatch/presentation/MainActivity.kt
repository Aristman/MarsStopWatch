package ru.marslab.marsstopwatch.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject
import ru.marslab.marsstopwatch.databinding.ActivityMainBinding

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
        mainViewModel.ticker[0].observe(this) {
            binding.textTimeOne.text = it
        }
        mainViewModel.ticker[1].observe(this) {
            binding.textTimeTwo.text = it
        }
    }

    private fun initListeners() {
        binding.run {
            buttonStartOne.setOnClickListener {
                mainViewModel.timerStart(1)
            }
            buttonPauseOne.setOnClickListener {
                mainViewModel.timerPause(1)
            }
            buttonStopOne.setOnClickListener {
                mainViewModel.timerStop(1)
            }
            buttonStartTwo.setOnClickListener {
                mainViewModel.timerStart(2)
            }
            buttonPauseTwo.setOnClickListener {
                mainViewModel.timerPause(2)
            }
            buttonStopTwo.setOnClickListener {
                mainViewModel.timerStop(2)
            }
        }
    }
}


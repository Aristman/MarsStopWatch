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
        mainViewModel.ticker.observe(this) {
            binding.textTime.text = it
        }
    }

    private fun initListeners() {
        binding.run {
            buttonStart.setOnClickListener {
                mainViewModel.timerStart()
            }
            buttonPause.setOnClickListener {
                mainViewModel.timerPause()
            }
            buttonStop.setOnClickListener {
                mainViewModel.timerStop()
            }
        }
    }
}


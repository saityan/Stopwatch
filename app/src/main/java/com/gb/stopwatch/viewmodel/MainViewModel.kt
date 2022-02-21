package com.gb.stopwatch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.stopwatch.model.*
import kotlinx.coroutines.*

internal class MainViewModel () : ViewModel(), Stopwatch {
    val liveData: MutableLiveData<String> = MutableLiveData()
    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                ElapsedTimeCalculator()
            ),
            ElapsedTimeCalculator(),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )

    init {
        viewModelScope.launch {
            stopwatchListOrchestrator.ticker
                .collect { data ->
                    liveData.value = data
                }
        }
    }

    override fun start() {
        stopwatchListOrchestrator.start()
    }

    override fun pause() {
        stopwatchListOrchestrator.pause()
    }

    override fun stop() {
        stopwatchListOrchestrator.stop()
    }
}

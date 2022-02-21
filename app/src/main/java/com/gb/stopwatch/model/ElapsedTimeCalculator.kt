package com.gb.stopwatch.model

class ElapsedTimeCalculator() {
    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = Timestamp.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}

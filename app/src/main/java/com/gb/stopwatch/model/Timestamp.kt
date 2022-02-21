package com.gb.stopwatch.model

object Timestamp : TimestampProvider {
    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}

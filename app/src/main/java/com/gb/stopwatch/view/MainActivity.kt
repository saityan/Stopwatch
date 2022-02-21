package com.gb.stopwatch.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gb.stopwatch.R
import com.gb.stopwatch.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeStopwatch()
        initButtons()
    }

    private fun observeStopwatch() {
        val textView = findViewById<TextView>(R.id.text_time)
        getViewModel().liveData.observe(this)
        { data -> if(!data.isNullOrBlank())
            textView.text = data
        }
    }

    private fun initButtons() {
        findViewById<Button>(R.id.button_start).setOnClickListener {
            getViewModel().start()
        }
        findViewById<Button>(R.id.button_pause).setOnClickListener {
            getViewModel().pause()
        }
        findViewById<Button>(R.id.button_stop).setOnClickListener {
            getViewModel().stop()
        }
    }

    private fun getViewModel() = ViewModelProvider(this).get(MainViewModel::class.java)
}

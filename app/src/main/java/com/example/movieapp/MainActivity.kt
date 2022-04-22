package com.example.movieapp

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
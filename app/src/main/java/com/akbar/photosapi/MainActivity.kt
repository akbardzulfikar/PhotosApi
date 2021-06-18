package com.akbar.photosapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akbar.photosapi.databinding.ActivityMainBinding
import com.akbar.photosapi.util.resetStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetStatusBarColor()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
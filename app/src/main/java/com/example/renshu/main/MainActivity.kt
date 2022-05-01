package com.example.renshu.main

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val textView = binding.randomText
        textView.text = "Hello World 2.0!"
    }
}

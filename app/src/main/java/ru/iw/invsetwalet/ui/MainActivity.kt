package ru.iw.invsetwalet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        println("dog1")
    }
}
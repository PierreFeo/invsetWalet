package ru.iw.invsetwalet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val toolbar = findViewById<Toolbar>(R.id.toolbarUp)
//        setSupportActionBar(toolbar)
            //TODO create global toolbar




    }
}
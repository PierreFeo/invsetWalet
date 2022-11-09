package ru.iw.invsetwalet.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.iw.invsetwalet.R
import ru.iw.invsetwalet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val toolbar = findViewById<Toolbar>(R.id.toolbarUp)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_rrow_back_24dp)


        val navController = binding.navHostFragment.getFragment<NavHostFragment>().navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) toolbar.visibility = View.GONE else toolbar.visibility = View.VISIBLE

        }
        toolbar.setNavigationOnClickListener { navController.navigateUp() }
    }
}

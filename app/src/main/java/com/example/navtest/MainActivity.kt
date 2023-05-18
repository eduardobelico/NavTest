package com.example.navtest

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setDrawerNavigation()
        setAppBarNavigation()
        setAppBarColor()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHost)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setDrawerNavigation() {
        val navHostFragment =
            (supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment)
        navController = navHostFragment.navController
        drawerLayout = binding.drawerLayout
        binding.navigationView.setupWithNavController(navController)
    }

    private fun setAppBarNavigation() {
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setAppBarColor() {
        listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                if (destination.id == R.id.frag1) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.color_primary_variant)))
                } else if (destination.id == R.id.frag2) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.color_secondary_variant)))
                }
            }
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }
}

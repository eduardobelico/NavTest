package com.example.navtest

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.navtest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listener: NavController.OnDestinationChangedListener
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNavController()
        setDrawerNavigation()
        setBottomNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHost)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)
    }

    private fun setNavController() {
        val navHostFragment =
            (supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment)
        navController = navHostFragment.navController
        binding.navigationView.setupWithNavController(navController)
    }

    private fun setDrawerNavigation() {
        drawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        listener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
                if (destination.id == R.id.frag1) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.color_primary_variant)))
                } else if (destination.id == R.id.frag2) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.color_secondary_variant)))
                } else if (destination.id == R.id.frag3) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.color_terciary_variant)))
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

    private fun setBottomNavigation() {
        bottomNavigationView = binding.bottomNav
        bottomNavigationView.setupWithNavController(navController)
    }
}
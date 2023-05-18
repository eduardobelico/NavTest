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
//    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            (supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment)
        navController = navHostFragment.navController
        drawerLayout = binding.drawerLayout
        binding.navigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHost)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


//        binding.apply {
//            toggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout, R.string.open, R.string.close)
//            drawerLayout.addDrawerListener(toggle)
//            toggle.syncState()
//
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//            navigationView.setNavigationItemSelectedListener { item ->
//                when(item.itemId){
//                    R.id.fragOne -> {
//                        findNavController(R.id.navHost).navigate(Frag2Directions.navigateToFrag1())
//                    }
//                    R.id.fragTwo -> {
//                        findNavController(R.id.navHost).navigate(Frag1Directions.navigateToFrag2())
//                    }
//                }
//                true
//            }
//        }
//
//
//
//    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (toggle.onOptionsItemSelected(item)) {
//            true
//        }
//        return super.onOptionsItemSelected(item)
//    }

}
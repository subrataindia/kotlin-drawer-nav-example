package com.example.kotlindrawernavapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    // Global Declaration so that can be accessed on all methods
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.layout_drawer)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Functionality for the nav drawer items
        val navView = findViewById<NavigationView>(R.id.nav_drawer)
        navView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            when(menuItem.itemId){
                R.id.nav_home -> inflateFragment(HomeFragment.newInstance(), menuItem.title.toString())
                R.id.nav_about -> inflateFragment(AboutFragment.newInstance(), menuItem.title.toString())
                R.id.nav_settings -> inflateFragment(SettingsFragment.newInstance(), menuItem.title.toString())
                R.id.nav_login -> inflateFragment(LoginFragment.newInstance(), menuItem.title.toString())
            }
            true;
        }

    }

    // Handle click on hamburger menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun inflateFragment(newInstance: Fragment, title: String) {
        val transaction = supportFragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_1, newInstance);
        transaction.commit();

        drawerLayout.closeDrawers()
        setTitle(title)
    }
}
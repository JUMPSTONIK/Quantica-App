package com.example.quantica.Activities

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.quantica.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_nav_drawer)

        var toolbar : Toolbar = findViewById(R.id.Toolbar)
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.nav_host_fragment_container)
        val bottom_navigation : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottom_navigation.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController)

        // Drawer Navigation
        val navigationView: NavigationView = findViewById(R.id.navigation_view)
        val drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        //maybe the Next line can me omitted
        NavigationUI.setupWithNavController(navigationView, navController)
        val btnDrawer: ImageView = findViewById(R.id.btnDrawer)
        navigationView.itemIconTintList

//        navigationView.setNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.log_out -> logOutFun()
//            }
//            true
//        }

        if(!drawerLayout.isDrawerOpen(Gravity.LEFT)){
            btnDrawer.setOnClickListener{
                drawerLayout.openDrawer(Gravity.LEFT)
            }
        }else{
            drawerLayout.closeDrawer(Gravity.LEFT)
        }
    }

    fun logOutFun(){
//        val prefs = getSharedPreferences(getString(R.string.prefs_file), MODE_PRIVATE).edit()
//        prefs.clear()
//        prefs.apply()
        val intent = Intent(this, Main::class.java)
        startActivity(intent)
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
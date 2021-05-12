package com.example.Quantica.Activities

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.Gravity.LEFT
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.Quantica.Classes.SliderAdapter
import com.example.Quantica.Classes.SliderItem
import com.example.Quantica.R
import com.google.android.material.navigation.NavigationView
import kotlin.math.abs

class home : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2

    @Suppress("DEPRECATION")
    private val sliderHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawelayout)

        /**
         * this is the code to animate the background
         *
         */
        val layout = findViewById<ConstraintLayout>(R.id.home_layout)
        val animDrawable = layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(2000)
        animDrawable.start()

        /**
         * this is for the Drawer Menu
         */
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer)
        val navView: NavigationView = findViewById(R.id.navigation_view)
        val btnDrawer: ImageView = findViewById(R.id.btnDrawer)
        navView.itemIconTintList


        if(!drawerLayout.isDrawerOpen(LEFT)){
            btnDrawer.setOnClickListener{
                drawerLayout.openDrawer(LEFT)
            }
        }else{
            drawerLayout.closeDrawer(LEFT)
        }


        /**
         * here i put the outline to all the TextViews
         */
        val tVArchitect: TextView = findViewById(R.id.tVArchitect)
        val tVGoodLiving: TextView = findViewById(R.id.tVGoodLiving)
        val tVMeta: TextView = findViewById(R.id.tVMeta)
        val tVMusikon: TextView = findViewById(R.id.tVMusikon)
        val tVNamaste: TextView = findViewById(R.id.tVNamaste)
        val tVNewEntrepreneurs: TextView = findViewById(R.id.tVNewEntrepreneurs)

         tVArchitect.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
         tVGoodLiving.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
         tVMeta.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
         tVMusikon.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
         tVNamaste.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
         tVNewEntrepreneurs.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)

        /**
         * The next section is the ViewPager code
         */
        viewPager2 = findViewById(R.id.viewPager_ImageSlider)

        val sliderItems : MutableList<SliderItem> = ArrayList()
        sliderItems.add(SliderItem(R.mipmap.image1))
        sliderItems.add(SliderItem(R.mipmap.image2))
        sliderItems.add(SliderItem(R.mipmap.image3))
        sliderItems.add(SliderItem(R.mipmap.image4))
        sliderItems.add(SliderItem(R.mipmap.image5))

        viewPager2.adapter = SliderAdapter(sliderItems, viewPager2)

        //con el siguiente codigo se dara el estilo a las imagenes
        //Tambien se dara el estilo del scrolling entre ellas
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer{ page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.95f + r *0.40f
        }

        viewPager2.setPageTransformer(compositePageTransformer)
        //aca le damos las propiedades de hacer slide cada 3 segundos
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)//estos 3000 es equivalente a 3 segundos
            }
        })
    }

    private  val sliderRunnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }
}
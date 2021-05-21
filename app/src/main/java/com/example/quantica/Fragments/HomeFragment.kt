package com.example.quantica.Fragments

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.quantica.Classes.SliderAdapter
import com.example.quantica.Classes.SliderItem
import com.example.quantica.R
import com.example.quantica.databinding.FragmentHomeBinding
import kotlin.math.abs

class HomeFragment : Fragment() {

    private  var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root

    }

    private lateinit var viewPager2: ViewPager2
    private val sliderHandler = Handler()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * this is the code to animate the background
         *
         */
        val layout = binding?.homeLayout
        val animDrawable = layout?.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(2000)
        animDrawable.start()

        /**
         * The next section is the ViewPager code
         */
        viewPager2 = binding?.viewPagerImageSlider!!

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

        /**
         * here i put the outline to all the TextViews
         */
        val tVArchitect = binding!!.tVArchitect
        val tVGoodLiving = binding!!.tVGoodLiving
        val tVMeta: TextView = binding!!.tVMeta
        val tVMusikon: TextView = binding!!.tVMusikon
        val tVNamaste: TextView = binding!!.tVNamaste
        val tVNewEntrepreneurs = binding!!.tVNewEntrepreneurs

        tVArchitect.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
        tVGoodLiving.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
        tVMeta.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
        tVMusikon.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
        tVNamaste.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)
        tVNewEntrepreneurs.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
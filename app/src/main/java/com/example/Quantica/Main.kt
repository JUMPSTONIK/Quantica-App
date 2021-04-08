package com.example.Quantica

import android.content.Intent
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class Main : AppCompatActivity() {
    override fun onResume() {

        super.onResume()
        playVideo()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(2000)
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playVideo()

        val log_in_Btn = findViewById<Button>(R.id.log_in_button)

        log_in_Btn.setOnClickListener{
            val intent = Intent(this, log_in::class.java)
            startActivity(intent)
        }

        val register_Btn = findViewById<Button>(R.id.register_button)

        register_Btn.setOnClickListener{
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }

        if (Locale.getDefault().displayLanguage == "español"){
            log_in_Btn.setText(R.string.iniciar_sesion)
            register_Btn.setText(R.string.registrarse)
        }

        if (Locale.getDefault().displayLanguage == "English"){
            log_in_Btn.setText(R.string.log_in)
            register_Btn.setText(R.string.register)
        }

        //Log.d("Idioma", Locale.getDefault().getDisplayLanguage())
    }
    fun playVideo(){
        val  videoView = findViewById<VideoView>(R.id.videoView)
        val offlineUri = Uri.parse("android.resource://$packageName/${R.raw.introduction_video}")
        videoView.setVideoURI(offlineUri)
        videoView.requestFocus()
        videoView.start()
        videoView.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })
    }
}
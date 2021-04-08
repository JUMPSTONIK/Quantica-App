package com.example.Quantica

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val layout = findViewById<ConstraintLayout>(R.id.Register_layout)

        val animDrawable = layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        val tVTitulo = findViewById<TextView>(R.id.RegisteTitle)
        tVTitulo.setShadowLayer(1.6f,2.5f,2.3f, Color.BLACK)

        val tVSubtitulo = findViewById<TextView>(R.id.RegisterSubtitle)
        tVSubtitulo.setShadowLayer(1.6f,2.0f,1.8f, Color.BLACK)

        val tVSendToRegisterTextPart1 = findViewById<TextView>(R.id.tVSendToLoginTextPart1)
        tVSendToRegisterTextPart1.setShadowLayer(1.6f,1.7f,1.5f, Color.BLACK);

        val tVSendToRegisterTextPart2 = findViewById<TextView>(R.id.tVSendToLoginTextPart2)
        tVSendToRegisterTextPart2.setShadowLayer(1.6f,1.7f,1.5f, Color.BLACK);

        val BtnReturnRegister = findViewById<ImageButton>(R.id.BtnReturnRegister)

        BtnReturnRegister.setOnClickListener{
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }

        tVSendToRegisterTextPart2.setOnClickListener{
            val intent = Intent(this, log_in::class.java)
            startActivity(intent)
        }

    }
}
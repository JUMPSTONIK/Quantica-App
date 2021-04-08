package com.example.Quantica

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class log_in : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val layout = findViewById<ConstraintLayout>(R.id.log_in_layout)

        val animDrawable = layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        val log_in_Btn = findViewById<Button>(R.id.LoginBtn)
        val eTEmail = findViewById<EditText>(R.id.editTextEmailAddressLogin)
        val eTPassword = findViewById<EditText>(R.id.editTextPasswordLogin)
        val tVor = findViewById<TextView>(R.id.tVor)

        val tVTitle = findViewById<TextView>(R.id.LoginTitle)
        tVTitle.setShadowLayer(1.6f,2.5f,2.3f, Color.BLACK)

        val tVSubtitle = findViewById<TextView>(R.id.LoginSubtitle)
        tVSubtitle.setShadowLayer(1.6f,2.0f,1.8f, Color.BLACK)

        val tVforgetTextPart1 = findViewById<TextView>(R.id.tVforgetTextPart1)
        tVforgetTextPart1.setShadowLayer(1.6f,1.7f,1.5f, Color.BLACK)

        val tVforgetTextPart2 = findViewById<TextView>(R.id.tVforgetTextPart2)
        tVforgetTextPart2.setShadowLayer(1.6f,1.7f,1.5f, Color.BLACK)

        val tVSendToRegisterTextPart1 = findViewById<TextView>(R.id.tVSendToRegisterTextPart1)
        tVSendToRegisterTextPart1.setShadowLayer(1.6f,1.7f,1.5f, Color.BLACK);

        val tVSendToRegisterTextPart2 = findViewById<TextView>(R.id.tVSendToRegisterTextPart2)
        tVSendToRegisterTextPart2.setShadowLayer(1.6f,1.7f,1.5f, Color.BLACK);

        val ReturnLoginBtn = findViewById<ImageButton>(R.id.BtnReturnLogin)

        ReturnLoginBtn.setOnClickListener{
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }

        tVSendToRegisterTextPart2.setOnClickListener{
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }

        if (Locale.getDefault().displayLanguage == "español"){
            tVTitle.setText(R.string.titulo_inicio_de_sesion)
            tVSubtitle.setText(R.string.subtitulo_inicio_de_sesion)
            eTEmail.setHint(R.string.correo)
            eTPassword.setHint(R.string.contraseña)
            log_in_Btn.setText(R.string.iniciar_sesion)
            tVforgetTextPart1.setText(R.string.olvidaste_constraseña_part1)
            tVforgetTextPart2.setText(R.string.olvidaste_constraseña_part2)
            tVSendToRegisterTextPart1.setText(R.string.redirigir_registro_part1)
            tVSendToRegisterTextPart2.setText(R.string.redirigir_registro_part2)
            tVor.setText(R.string.o)
        }

        if (Locale.getDefault().displayLanguage == "English"){
            tVTitle.setText(R.string.login_tittle)
            tVSubtitle.setText(R.string.login_subtitle)
            eTEmail.setHint(R.string.email)
            eTPassword.setHint(R.string.password)
            log_in_Btn.setText(R.string.log_in)
            tVforgetTextPart1.setText(R.string.forgot_password_part1)
            tVforgetTextPart2.setText(R.string.forgot_password_part2)
            tVSendToRegisterTextPart1.setText(R.string.redirect_register_part1)
            tVSendToRegisterTextPart2.setText(R.string.redirect_register_part2)
            tVor.setText(R.string.or)
        }

    }
}


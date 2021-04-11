package com.example.Quantica

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class log_in : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val layout = findViewById<LinearLayout>(R.id.sign_in_layout)

        val animDrawable = layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        val log_in_Btn = findViewById<TextView>(R.id.loginBtn)
        val eTEmail = findViewById<EditText>(R.id.editTextEmailAddressLogin)
        val eTPassword = findViewById<EditText>(R.id.editTextPasswordLogin)


        val tVTitle = findViewById<TextView>(R.id.LoginTitle)
        tVTitle.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)

        val tVSubtitle = findViewById<TextView>(R.id.LoginSubtitle)
        tVSubtitle.setShadowLayer(1.6f, 2.0f, 1.8f, Color.BLACK)

        val tVforgetTextPart1 = findViewById<TextView>(R.id.tVforgetTextPart1)
        tVforgetTextPart1.setShadowLayer(1.6f, 1.7f, 1.5f, Color.BLACK)

        val tVforgetTextPart2 = findViewById<TextView>(R.id.tVforgetTextPart2)
        tVforgetTextPart2.setShadowLayer(1.6f, 1.7f, 1.5f, Color.BLACK)

        val tVSendToRegisterTextPart1 = findViewById<TextView>(R.id.tVSendToRegisterTextPart1)
        tVSendToRegisterTextPart1.setShadowLayer(1.6f, 1.7f, 1.5f, Color.BLACK)

        val tVSendToRegisterTextPart2 = findViewById<TextView>(R.id.tVSendToRegisterTextPart2)
        tVSendToRegisterTextPart2.setShadowLayer(1.6f, 1.7f, 1.5f, Color.BLACK)

        val tVOr = findViewById<TextView>(R.id.tVor)
        tVOr.setShadowLayer(1.6f, 1.7f, 1.5f, Color.BLACK)

        val ReturnLoginBtn = findViewById<ImageView>(R.id.ReturnLoginBtn)

        ReturnLoginBtn.setOnClickListener{
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }

        tVSendToRegisterTextPart2.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)
        }

        if (Locale.getDefault().displayLanguage == "español") {
            tVTitle.setText(R.string.titulo_inicio_de_sesion)
            tVSubtitle.setText(R.string.subtitulo_inicio_de_sesion)
            eTEmail.setHint(R.string.correo)
            eTPassword.setHint(R.string.contraseña)
            log_in_Btn.setText(R.string.iniciar_sesion)
            tVforgetTextPart1.setText(R.string.olvidaste_constraseña_part1)
            tVforgetTextPart2.setText(R.string.olvidaste_constraseña_part2)
            tVOr.setText(R.string.o)
            tVSendToRegisterTextPart1.setText(R.string.redirigir_registro_part1)
            tVSendToRegisterTextPart2.setText(R.string.redirigir_registro_part2)
        }
        else{
            if (Locale.getDefault().displayLanguage == "English" || Locale.getDefault().displayLanguage != "English"  ) {
                tVTitle.setText(R.string.login_tittle)
                tVSubtitle.setText(R.string.login_subtitle)
                eTEmail.setHint(R.string.email)
                eTPassword.setHint(R.string.password)
                log_in_Btn.setText(R.string.log_in)
                tVforgetTextPart1.setText(R.string.forgot_password_part1)
                tVforgetTextPart2.setText(R.string.forgot_password_part2)
                tVOr.setText(R.string.or)
                tVSendToRegisterTextPart1.setText(R.string.redirect_register_part1)
                tVSendToRegisterTextPart2.setText(R.string.redirect_register_part2)
            }
        }

    }
}


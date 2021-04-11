package com.example.Quantica

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.*

class register() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val layout = findViewById<LinearLayout>(R.id.sign_in_layout)

        val animDrawable = layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        val register_Btn = findViewById<TextView>(R.id.registerBtn)
        val eTUser = findViewById<EditText>(R.id.editTextUserRegister)
        val eTEmail = findViewById<EditText>(R.id.editTextEmailAddressRegister)
        val eTPassword = findViewById<EditText>(R.id.editTextPasswordRegister)
        val eTPasswordConfirm = findViewById<EditText>(R.id.editTextTextPasswordConfirmRegister)

        val tVTitle = findViewById<TextView>(R.id.RegisterTitle)
        tVTitle.setShadowLayer(1.6f, 2.5f, 2.3f, Color.BLACK)

        val tVSubtitle = findViewById<TextView>(R.id.RegisterSubtitle)
        tVSubtitle.setShadowLayer(1.6f, 2.0f, 1.8f, Color.BLACK)
//
        val tVSendToLoginTextPart1 = findViewById<TextView>(R.id.tVSendToLoginTextPart1)
        tVSendToLoginTextPart1.setShadowLayer(1.6f, 1.7f, 1.5f, Color.BLACK);

        val tVSendToLoginTextPart2 = findViewById<TextView>(R.id.tVSendToLoginTextPart2)
        tVSendToLoginTextPart2.setShadowLayer(1.6f, 1.7f, 1.5f, Color.BLACK);

        val BtnReturnRegister = findViewById<ImageView>(R.id.BtnReturnRegister)

        BtnReturnRegister.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }

        tVSendToLoginTextPart2.setOnClickListener {
            val intent = Intent(this, log_in::class.java)
            startActivity(intent)
       }


        if (Locale.getDefault().displayLanguage == "español") {
            register_Btn.setText(R.string.registrarse)
            tVTitle.setText(R.string.titulo_registro)
            tVSubtitle.setText(R.string.subtitulo_registro)
            eTUser.setHint(R.string.usuario)
            eTEmail.setHint(R.string.correo)
            eTPassword.setHint(R.string.contraseña)
            eTPasswordConfirm.setHint(R.string.repetir_contraseña)
            tVSendToLoginTextPart1.setText(R.string.redirigir_iniciar_sesion_part1)
            tVSendToLoginTextPart2.setText(R.string.redirigir_iniciar_sesion_part2)
        }
        else{
            if (Locale.getDefault().displayLanguage == "English" || Locale.getDefault().displayLanguage != "English") {
                register_Btn.setText(R.string.sign_up)
                tVTitle.setText(R.string.sign_up_title)
                tVSubtitle.setText(R.string.sign_up_subtitle)
                eTUser.setHint(R.string.user)
                eTEmail.setHint(R.string.email)
                eTPassword.setHint(R.string.password)
                eTPasswordConfirm.setHint(R.string.confirm_password)
                tVSendToLoginTextPart1.setText(R.string.redirect_login_part1)
                tVSendToLoginTextPart2.setText(R.string.redirect_login_part2)
            }
        }

    }
}
package com.example.Quantica.Activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import android.widget.*
import com.example.Quantica.R
import com.example.Quantica.Services.ServiceBuilder
import com.example.Quantica.Services.userService
import com.example.Quantica.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.KeyStore
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class register() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        /**
         * this is the code to animate the background
         */
        val layout = findViewById<LinearLayout>(R.id.sign_in_layout)
        val animDrawable = layout.background as AnimationDrawable
        animDrawable.setEnterFadeDuration(10)
        animDrawable.setExitFadeDuration(3000)
        animDrawable.start()

        /**
         * this section of vars and code is to get all the components in the layout and access
         * to their properties
         */
        val register_Btn = findViewById<TextView>(R.id.registerBtn)
        val BtnReturnToMain = findViewById<ImageView>(R.id.BtnReturnRegister)
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

        /**
         * this is the button to return to the Main activity
         */
        BtnReturnToMain.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }
        /**
         * this is the button to send the user to the sign in activity
         */
        tVSendToLoginTextPart2.setOnClickListener {
            val intent = Intent(this, log_in::class.java)
            startActivity(intent)
       }

        /**
         * this is the code for the Register button to add a new user, if everything was filled right
         */
        register_Btn.setOnClickListener{

            if (eTEmail.text.toString() != "" &&  eTUser.text.toString() != "" && eTPassword.text.toString() != "" && eTPasswordConfirm.text.toString() != ""){
                if (eTPassword.text.toString() == eTPasswordConfirm.text.toString()) {
                    val newUser = User()
                    newUser.email = eTEmail.text.toString()
                    Log.d("EMAIL", newUser.email.toString())
                    newUser.nickname = eTUser.text.toString()
                    Log.d("NICKNAME", newUser.nickname.toString())
                    newUser.password = eTPassword.text.toString()
                    Log.d("PASSWORD", newUser.password.toString())
                    val intent = Intent(this, home::class.java)
                    addNewUser(newUser, intent)
                }else{
                    Toast.makeText(this@register, "The passwords introduced are not the same. please repeat both passwords", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@register, "Please fill all the fields to create a new account", Toast.LENGTH_LONG).show()
            }

        }
        /**
         * Here i put all the text on every component that exist in the layout, depending on the phone's language
         */
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

    /**
     * this is the Function to do the register and create a new user.
     */
    private fun addNewUser(newUser: User, intent: Intent) {
        Log.d("THEUSER", newUser.toString())
        val userService = ServiceBuilder.buildService(userService::class.java)
        val requestCall = userService.addUser(newUser)

        requestCall.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                //TODO("Not yet implemented")
                if (response.isSuccessful){
                    //finish() //nos movemos a Main para ver si se añadio nuevo user
                    var newlyCreatedUser = response.body() //i case i need it.
                    Log.d("NEWUSERS", newlyCreatedUser.toString())
                    startActivity(intent)
                    Toast.makeText(this@register, "Your User was created", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this@register, "Failed creating new user. Nickname or email already exist", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                //TODO("Not yet implemented")
                Toast.makeText(this@register, "Failed to add items", Toast.LENGTH_LONG).show()
            }
        })
    }

}
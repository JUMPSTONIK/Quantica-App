package com.example.Quantica.Activities

import android.content.Intent
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.example.Quantica.R
import com.example.Quantica.Services.ServiceBuilder
import com.example.Quantica.Services.userService
import com.example.Quantica.models.User
import retrofit2.Call
import java.util.*
//import javax.security.auth.callback.Callback
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.HashMap

class Main : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(2000)
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playVideo()
        //loadUsers()
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
        else{
            if (Locale.getDefault().displayLanguage == "English" || Locale.getDefault().displayLanguage != "English"){
                log_in_Btn.setText(R.string.log_in)
                register_Btn.setText(R.string.sign_up)
            }
        }

    }
    private fun playVideo(){
        val  videoView = findViewById<VideoView>(R.id.videoView)
        val offlineUri = Uri.parse("android.resource://$packageName/${R.raw.introduction_video}")
        videoView.setVideoURI(offlineUri)
        videoView.requestFocus()
        videoView.start()
        videoView.setOnPreparedListener(OnPreparedListener { mp -> mp.isLooping = true })
    }
/*
    private fun loadUsers(){
        val userService = ServiceBuilder.buildService(userService::class.java)
        //esta es la variable encargada de realizar los HTTP resquest y response

        val filter = HashMap<String, String>()
        //filter["country"] = "Costa Rica"
        //filter["count"] = "1"

        //val requestCall = userService.getUserList()
        val requestCall = userService.getUserl(filter)

        requestCall.enqueue(object: Callback<List<User>>{
            //Si se obtiene algun response, entonces se ejecutara esta funcion.
            //fijarse en el STATUS code para saber si se logro o no el request
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                //TODO("Not yet implemented")
                if (response.isSuccessful){
                    ///si el status code esta entre 200 y 299
                    val userList = response.body()!!
                    Log.d("USERS", userList.toString())
                    Log.d("USERS", userList!![0].id_user.toString() + " " + userList!![0].nickname + " " + userList!![0].email + " " + userList!![0].password)
                }else{//Fallo a nivel de aplicacion
                    Toast.makeText(this@Main, "Failed to retrieve items", Toast.LENGTH_LONG).show()
                }
            }
            //Esta funcion es invocada en caso de un error en la red o estableciendo conexion con el server
            //o existiera algun error al crear el http request o procesando el http response
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                //TODO("Not yet implemented")
                Toast.makeText(this@Main, "Error ocurred $t", Toast.LENGTH_LONG).show()
            }
        })
    }
 */
    override fun onResume() {
        super.onResume()
        playVideo()
        //loadUsers()
    }

    override fun onPause() {
        super.onPause()
        playVideo()
    }
}



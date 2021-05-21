package com.example.quantica.Activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.quantica.Classes.*
import com.example.quantica.R
import com.example.quantica.Services.ServiceBuilder
import com.example.quantica.Services.userService
import com.example.quantica.models.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class log_in : AppCompatActivity() {

    private val RC_SIGN_IN: Int = 123
    lateinit var mGoogleSignInClient: GoogleSignInClient
    var gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Apptheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val googleSignInButton = findViewById<ImageView>(R.id.googleLoginBtn)
        val TwitterSingInButton = findViewById<ImageView>(R.id.twitterLoginBtn)
        TwitterSingInButton.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        googleSignInButton.setOnClickListener{
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        //en el () del acct tenia getActivity()
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            //val personName = acct.displayName
            //val personGivenName = acct.givenName
            //val personFamilyName = acct.familyName
            //val personEmail = acct.email
            //val personId = acct.id
            //val personPhoto: Uri? = acct.photoUrl
        }else{
            Log.d("NADA", "NOTHING WORKED")
        }

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

        log_in_Btn.setOnClickListener{

            validateSingIn(eTEmail.text.toString(), eTPassword.text.toString())

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

    private fun validateSingIn(email: String, password: String) {
        val etEmailLayout = findViewById<TextInputLayout>(R.id.etEmailLayout)
        val emailAccepted = validateEmail(email, etEmailLayout)
        val etPasswordLayout = findViewById<TextInputLayout>(R.id.etPasswordLayout)
        val passwordAccepted = validatePasswordSingIn(password, etPasswordLayout)

        if (emailAccepted && passwordAccepted){
            val intent = Intent(this, Dashboard::class.java)
            findUser(email, password, intent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        Log.d("ITSWORKING", "ITS IN SIDE THE HANDLE")
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            Log.d("DISPLAYNAME", account.toString())
            if (account != null) {
//                Log.d("DISPLAYNAME", account.displayName.toString())
//                Log.d("EMAIL", account.email.toString())
//                Log.d("FAMILYNAME", account.familyName.toString())
//                Log.d("GIVENNAME", account.givenName.toString())
//                Log.d("EMAIL", account.photoUrl.toString())
            }
            // Signed in successfully, show authenticated UI.

            //updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("SIGNINGOOGLE", "signInResult:failed code=" + e.statusCode)
            //Log.d("SIGNINGOOGLE", "DIDNT WORKED...")
            //updateUI(null)
        }

    }

    private  fun saveUserDataPrefs(email: String, type: String){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", type)
        prefs.apply()
    }

    private fun findUser(email: String, password: String, intent: Intent) {
        val userService = ServiceBuilder.buildService(userService::class.java)
        //esta es la variable encargada de realizar los HTTP resquest y response
        val filter = HashMap<String, String>()
        filter["email"] = email
        filter["password"] = password
        //val requestCall = userService.getUserList()
        val requestCall = userService.getUserLogin(filter)

        requestCall.enqueue(object : Callback<List<User>> {
            //Si se obtiene algun response, entonces se ejecutara esta funcion.
            //fijarse en el STATUS code para saber si se logro o no el request
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    //Log.d("USERFOUND", "user found: " + response.body().toString())
                    startActivity(intent)
                } else {//Fallo a nivel de aplicacion
                    Toast.makeText(this@log_in, "We couldn't find your account or email or password are incorrect", Toast.LENGTH_LONG).show()
                }
            }
            //Esta funcion es invocada en caso de un error en la red o estableciendo conexion con el server
            //o existiera algun error al crear el http request o procesando el http response
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@log_in, "Error ocurred $t", Toast.LENGTH_LONG).show()
            }
        })
    }
}


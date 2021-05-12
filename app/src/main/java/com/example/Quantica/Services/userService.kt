package com.example.Quantica.Services

import android.provider.ContactsContract
import com.example.Quantica.models.User
import retrofit2.Call
import retrofit2.http.*
import java.util.logging.Filter

interface userService {

    @GET("Users")
    fun getUserLogin(@QueryMap filter: HashMap<String, String>): Call<List<User>>
    //fun getUserList(): Call<List<User>>
    //fun getUserList(@Query("country") country: String?, @Query("count") count: Int?): Call<List<User>>
    //@GET("Users/email")
    //fun getUser(): Call<List<User>>

    @GET("Users/{email}")
    fun getUser(@Path("email") email: String): Call<User>

    @POST("user")
    fun addUser(@Body newUser: User): Call<User>
}
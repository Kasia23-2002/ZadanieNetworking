package com.github.kkoscielniak.usersapp.users

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDetails>
}

object RetrofitInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/";

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
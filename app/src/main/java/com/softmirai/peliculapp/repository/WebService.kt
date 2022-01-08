package com.softmirai.peliculapp.repository

import com.google.gson.GsonBuilder
import com.softmirai.peliculapp.application.AppConstants
import com.softmirai.peliculapp.data.model.MovieList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    /*Este web service es el encargado con  retrofit de ir a traer
    * informacion al servidor
    * */

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("api_key") apiKey: String): MovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

}


object RetrofitClient {
    //by lazy es para inicializar la variable unicamente cuando llamo a RetrofitClient.webservice
    val webservice by lazy {

        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL) //le paso la base url al builder que cree anteriormente en el objeto AppConstants
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) //el converte es para que toda la info que esta dento del json lo conviera al modelo que tengo dentro del data source
            .build().create(WebService::class.java)
    }
}
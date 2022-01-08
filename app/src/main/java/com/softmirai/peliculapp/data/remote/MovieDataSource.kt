package com.softmirai.peliculapp.data.remote

import com.softmirai.peliculapp.data.model.MovieList

class MovieDataSource {
    //creo los metodos que van a ir a buscar información al servidor de cada una de las peliculas

    fun getUpcomingMovies(): MovieList{
        return MovieList()
    }

    fun getTopRatedMovies(): MovieList{
        return  MovieList()
    }


    fun getPopularMovies(): MovieList{
        return MovieList()
    }

}
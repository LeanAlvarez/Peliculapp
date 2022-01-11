package com.softmirai.peliculapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.softmirai.peliculapp.R
import com.softmirai.peliculapp.core.Resource
import com.softmirai.peliculapp.data.remote.MovieDataSource
import com.softmirai.peliculapp.databinding.FragmentMovieBinding
import com.softmirai.peliculapp.presentation.MovieViewModel
import com.softmirai.peliculapp.presentation.MovieViewModelFactory
import com.softmirai.peliculapp.repository.MovieRepositoryImpl
import com.softmirai.peliculapp.repository.RetrofitClient


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var binding: FragmentMovieBinding

    //creo instancia de viewModel
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(MovieRepositoryImpl(MovieDataSource(RetrofitClient.webservice)))
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieBinding.bind(view)

      viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer {result ->
          when(result){
              is Resource.Loading ->{
                    Log.d("LiveData", "Loading...")
              }
              is Resource.Success ->{
                  Log.d("LiveData", "Upcoming: ${result.data.first}  \n TopRated: ${result.data.second} \n Popular:${result.data.third}")
              }
              is Resource.Failure ->{
                  Log.d("Error", "${result.exception}")
              }
          }
      })


    }
}
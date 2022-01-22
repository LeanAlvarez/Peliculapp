package com.softmirai.peliculapp.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.softmirai.peliculapp.R
import com.softmirai.peliculapp.core.Resource
import com.softmirai.peliculapp.data.model.Movie
import com.softmirai.peliculapp.data.remote.RemoteMovieDataSource
import com.softmirai.peliculapp.databinding.FragmentMovieBinding
import com.softmirai.peliculapp.presentation.MovieViewModel
import com.softmirai.peliculapp.presentation.MovieViewModelFactory
import com.softmirai.peliculapp.repository.MovieRepositoryImpl
import com.softmirai.peliculapp.repository.RetrofitClient
import com.softmirai.peliculapp.ui.movie.adapters.concat.MovieAdapter
import com.softmirai.peliculapp.ui.movie.adapters.concat.PopularConcatAdapter
import com.softmirai.peliculapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.softmirai.peliculapp.ui.movie.adapters.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding

    //creo instancia de viewModel
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(MovieRepositoryImpl(RemoteMovieDataSource(RetrofitClient.webservice)))
    }


    private lateinit var concatAdapter: ConcatAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieBinding.bind(view)

        //inicializo el concat Adapter
        concatAdapter = ConcatAdapter()

        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    result.data.first.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    result.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    result.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )

                    }

                    binding.rvMovies.adapter = concatAdapter

                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    Log.d("Error", "${result.exception}")
                }
            }
        })


    }

    override fun onMovieClick(movie: Movie) {
        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )
        findNavController().navigate(action)
    }
}
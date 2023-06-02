package com.example.movies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.api.ApiFactory
import com.example.movies.database.MovieDatabase
import com.example.movies.pojo.Movie
import com.example.movies.pojo.Review
import com.example.movies.pojo.Trailer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    private val movieDao = MovieDatabase.getInstance(application).movieDao()
    private val trailers: MutableLiveData<List<Trailer>> = MutableLiveData()
    private val reviews: MutableLiveData<List<Review>> = MutableLiveData()
    val getTrailers: LiveData<List<Trailer>> get() = trailers
    val getReviews: LiveData<List<Review>> get() = reviews

    fun getFavoriteMovie(id: Int) = movieDao.getFavoriteMovie(id)

    companion object {
        private const val TAG = "MovieDetailViewModel"
    }

    fun insertMovie(movie: Movie) {
        val disposable = movieDao.insertMovie(movie)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun removeMovie(id: Int) {
        val disposable = movieDao.removeMovie(id)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun loadReview(movieId: Int) {
        val disposable = ApiFactory.apiService.loadReviews(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.review }
            .subscribe({ reviews.value = it },
                { Log.e(TAG, "Error loadReview(): $it") })
    }

    fun loadTrailer(id: Int) {
        val disposable = ApiFactory.apiService.loadTrailers(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.trailers.trailerList }
            .subscribe({ trailers.value = it },
                { Log.e(TAG, "Error loadTrailer(): $it") })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
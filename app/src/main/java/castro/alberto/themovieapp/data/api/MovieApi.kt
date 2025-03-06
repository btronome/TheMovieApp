package castro.alberto.themovieapp.data.api

import castro.alberto.themovieapp.data.model.remote.MovieDetailsResponse
import castro.alberto.themovieapp.data.model.remote.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getPopularMovies(@Query("page") page: Long): PopularMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Long): MovieDetailsResponse
}

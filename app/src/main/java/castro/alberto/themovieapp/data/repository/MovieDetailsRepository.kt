package castro.alberto.themovieapp.data.repository

import castro.alberto.themovieapp.domain.model.MovieDetails

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Long): Result<MovieDetails>
}

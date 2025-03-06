package castro.alberto.themovieapp.data.repository

import castro.alberto.themovieapp.domain.model.PopularMovies

interface PopularMoviesRepository {
    suspend fun getPopularMovies(page: Long): Result<PopularMovies>
}

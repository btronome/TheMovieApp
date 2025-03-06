package castro.alberto.themovieapp.data.datasource.remote

import castro.alberto.themovieapp.data.api.MovieApi
import castro.alberto.themovieapp.data.exception.mapToDataException
import castro.alberto.themovieapp.data.mapper.remote.toDomainModel
import castro.alberto.themovieapp.domain.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailsRemoteDataSource @Inject constructor(private val api: MovieApi) {
    suspend fun getMovieDetails(movieId: Long): Result<MovieDetails> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(api.getMovieDetails(movieId).toDomainModel())
            } catch (exception: Throwable) {
                Result.failure(exception.mapToDataException())
            }
        }
    }
}

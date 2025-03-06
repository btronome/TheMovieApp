package castro.alberto.themovieapp.data.repository.implementation

import castro.alberto.themovieapp.data.datasource.remote.MovieDetailsRemoteDataSource
import castro.alberto.themovieapp.data.exception.mapToDataException
import castro.alberto.themovieapp.data.repository.MovieDetailsRepository
import castro.alberto.themovieapp.domain.model.MovieDetails
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailsRemoteDataSource
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Long): Result<MovieDetails> {
        return try {
            Result.success(remoteDataSource.getMovieDetails(movieId).getOrThrow())
        } catch (exception: Exception) {
            Result.failure(exception.mapToDataException())
        }
    }
}

package castro.alberto.themovieapp.data.repository.implementation

import castro.alberto.themovieapp.data.datasource.local.MovieDetailsLocalDataSource
import castro.alberto.themovieapp.data.datasource.remote.MovieDetailsRemoteDataSource
import castro.alberto.themovieapp.data.exception.DataException
import castro.alberto.themovieapp.data.exception.mapToDataException
import castro.alberto.themovieapp.data.repository.MovieDetailsRepository
import castro.alberto.themovieapp.domain.exception.mapToDomainException
import castro.alberto.themovieapp.domain.model.MovieDetails
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailsRemoteDataSource,
    private val localDataSource: MovieDetailsLocalDataSource
) : MovieDetailsRepository {
    override suspend fun getMovieDetails(movieId: Long): Result<MovieDetails> {
        return try {
            val remoteData = remoteDataSource.getMovieDetails(movieId).getOrThrow()
            localDataSource.insertMovieDetails(remoteData)
            Result.success(remoteData)
        } catch (exception: Exception) {
            if (exception is DataException.NetworkError) {
                val localData = localDataSource.getMovieDetails(movieId).getOrThrow()
                localData?.let {
                    Result.success(localData)
                } ?: run {
                    Result.failure(exception.mapToDomainException())
                }
            } else {
                Result.failure(exception.mapToDataException())
            }
        }
    }
}

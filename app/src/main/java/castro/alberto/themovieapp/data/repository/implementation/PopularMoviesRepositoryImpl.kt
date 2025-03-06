package castro.alberto.themovieapp.data.repository.implementation

import castro.alberto.themovieapp.data.datasource.local.PopularMoviesLocalDataSource
import castro.alberto.themovieapp.data.datasource.remote.PopularMoviesRemoteDataSource
import castro.alberto.themovieapp.data.exception.DataException
import castro.alberto.themovieapp.data.exception.mapToDataException
import castro.alberto.themovieapp.data.repository.PopularMoviesRepository
import castro.alberto.themovieapp.domain.exception.mapToDomainException
import castro.alberto.themovieapp.domain.model.PopularMovies
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: PopularMoviesRemoteDataSource,
    private val localDataSource: PopularMoviesLocalDataSource
) : PopularMoviesRepository {
    override suspend fun getPopularMovies(page: Long): Result<PopularMovies> {
        return try {
            val remoteData = remoteDataSource.getPopularMovies(page).getOrThrow()
            localDataSource.insertPopularMovies(remoteData)
            Result.success(remoteData)
        } catch (exception: Exception) {
            if (exception is DataException.NetworkError) {
                val localData = localDataSource.getPopularMovies(page).getOrThrow()
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

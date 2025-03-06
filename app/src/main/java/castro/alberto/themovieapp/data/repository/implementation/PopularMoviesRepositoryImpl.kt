package castro.alberto.themovieapp.data.repository.implementation

import castro.alberto.themovieapp.data.datasource.remote.PopularMoviesRemoteDataSource
import castro.alberto.themovieapp.data.exception.mapToDataException
import castro.alberto.themovieapp.data.repository.PopularMoviesRepository
import castro.alberto.themovieapp.domain.model.PopularMovies
import javax.inject.Inject

class PopularMoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: PopularMoviesRemoteDataSource
) : PopularMoviesRepository {
    override suspend fun getPopularMovies(page: Long): Result<PopularMovies> {
        return try {
            Result.success(remoteDataSource.getPopularMovies(page).getOrThrow())
        } catch (exception: Exception) {
            Result.failure(exception.mapToDataException())
        }
    }
}

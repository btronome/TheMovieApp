package castro.alberto.themovieapp.data.datasource.remote

import castro.alberto.themovieapp.data.api.MovieApi
import castro.alberto.themovieapp.data.exception.mapToDataException
import castro.alberto.themovieapp.data.mapper.remote.toDomainModel
import castro.alberto.themovieapp.domain.model.PopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularMoviesRemoteDataSource @Inject constructor(private val api: MovieApi) {
    suspend fun getPopularMovies(page: Long): Result<PopularMovies> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(api.getPopularMovies(page).toDomainModel())
            } catch (exception: Throwable) {
                Result.failure(exception.mapToDataException())
            }
        }
    }
}

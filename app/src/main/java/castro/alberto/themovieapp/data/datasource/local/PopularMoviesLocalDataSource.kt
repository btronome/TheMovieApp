package castro.alberto.themovieapp.data.datasource.local

import castro.alberto.themovieapp.data.database.dao.PopularMoviesDao
import castro.alberto.themovieapp.data.exception.mapToDataException
import castro.alberto.themovieapp.data.mapper.local.toDomainModel
import castro.alberto.themovieapp.data.mapper.local.toEntityModel
import castro.alberto.themovieapp.domain.model.PopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularMoviesLocalDataSource @Inject constructor(private val dao: PopularMoviesDao) {
    suspend fun getPopularMovies(page: Long): Result<PopularMovies?> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(dao.getPopularMovies(page)?.toDomainModel())
            } catch (exception: Throwable) {
                Result.failure(exception.mapToDataException())
            }
        }
    }

    suspend fun insertPopularMovies(popularMovies: PopularMovies) {
        return withContext(Dispatchers.IO) {
            try {
                dao.insertPopularMovies(popularMovies.toEntityModel())
                Result.success(Unit)
            } catch (exception: Throwable) {
                Result.failure(exception.mapToDataException())
            }
        }
    }
}

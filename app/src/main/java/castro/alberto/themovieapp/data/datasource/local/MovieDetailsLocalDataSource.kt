package castro.alberto.themovieapp.data.datasource.local

import castro.alberto.themovieapp.data.database.dao.MovieDetailsDao
import castro.alberto.themovieapp.data.exception.mapToDataException
import castro.alberto.themovieapp.data.mapper.local.toDomainModel
import castro.alberto.themovieapp.data.mapper.local.toEntityModel
import castro.alberto.themovieapp.domain.model.MovieDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailsLocalDataSource @Inject constructor(private val dao: MovieDetailsDao) {
    suspend fun getMovieDetails(movieId: Long): Result<MovieDetails?> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(dao.getMovieDetails(movieId)?.toDomainModel())
            } catch (exception: Throwable) {
                Result.failure(exception.mapToDataException())
            }
        }
    }

    suspend fun insertMovieDetails(movieDetails: MovieDetails) {
        return withContext(Dispatchers.IO) {
            try {
                dao.insertMovieDetails(movieDetails.toEntityModel())
                Result.success(Unit)
            } catch (exception: Throwable) {
                Result.failure(exception.mapToDataException())
            }
        }
    }
}

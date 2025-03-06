package castro.alberto.themovieapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import castro.alberto.themovieapp.data.model.local.MovieDetailsEntity

@Dao
interface MovieDetailsDao {
    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    fun getMovieDetails(movieId: Long): MovieDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetails: MovieDetailsEntity)
}

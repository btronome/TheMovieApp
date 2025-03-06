package castro.alberto.themovieapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import castro.alberto.themovieapp.data.model.local.PopularMoviesEntity

@Dao
interface PopularMoviesDao {
    @Query("SELECT * FROM popular_movies WHERE page = :page")
    fun getPopularMovies(page: Long): PopularMoviesEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(popularMovies: PopularMoviesEntity)
}

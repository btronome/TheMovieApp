package castro.alberto.themovieapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movies")
data class PopularMoviesEntity(
    @PrimaryKey
    val page: Long,
    val results: List<MovieEntity>,
    val totalPages: Long,
    val totalResults: Long
)

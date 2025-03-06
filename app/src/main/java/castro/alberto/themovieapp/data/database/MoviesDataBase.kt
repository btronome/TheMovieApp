package castro.alberto.themovieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import castro.alberto.themovieapp.data.database.converter.LongListConverter
import castro.alberto.themovieapp.data.database.converter.GenreListConverter
import castro.alberto.themovieapp.data.database.converter.MovieListConverter
import castro.alberto.themovieapp.data.database.dao.MovieDetailsDao
import castro.alberto.themovieapp.data.database.dao.PopularMoviesDao
import castro.alberto.themovieapp.data.model.local.GenreEntity
import castro.alberto.themovieapp.data.model.local.MovieDetailsEntity
import castro.alberto.themovieapp.data.model.local.MovieEntity
import castro.alberto.themovieapp.data.model.local.PopularMoviesEntity

@Database(
    entities = [
        PopularMoviesEntity::class,
        MovieEntity::class,
        MovieDetailsEntity::class,
        GenreEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(LongListConverter::class, MovieListConverter::class, GenreListConverter::class)
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun popularMoviesDao(): PopularMoviesDao
    abstract fun movieDetailsDao(): MovieDetailsDao
}

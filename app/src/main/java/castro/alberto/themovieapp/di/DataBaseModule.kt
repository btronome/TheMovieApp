package castro.alberto.themovieapp.di

import android.content.Context
import androidx.room.Room
import castro.alberto.themovieapp.data.database.MoviesDataBase
import castro.alberto.themovieapp.data.database.dao.MovieDetailsDao
import castro.alberto.themovieapp.data.database.dao.PopularMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): MoviesDataBase {
        return Room.databaseBuilder(context, MoviesDataBase::class.java, "movies_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesPopularMoviesDao(dataBase: MoviesDataBase): PopularMoviesDao {
        return dataBase.popularMoviesDao()
    }

    @Provides
    fun providesMovieDetailsDao(dataBase: MoviesDataBase): MovieDetailsDao {
        return dataBase.movieDetailsDao()
    }
}

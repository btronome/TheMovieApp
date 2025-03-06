package castro.alberto.themovieapp.di

import castro.alberto.themovieapp.data.api.MovieApi
import castro.alberto.themovieapp.data.database.dao.MovieDetailsDao
import castro.alberto.themovieapp.data.database.dao.PopularMoviesDao
import castro.alberto.themovieapp.data.datasource.local.MovieDetailsLocalDataSource
import castro.alberto.themovieapp.data.datasource.local.PopularMoviesLocalDataSource
import castro.alberto.themovieapp.data.datasource.remote.MovieDetailsRemoteDataSource
import castro.alberto.themovieapp.data.datasource.remote.PopularMoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun providesPopularMoviesRemoteDataSource(api: MovieApi): PopularMoviesRemoteDataSource {
        return PopularMoviesRemoteDataSource(api)
    }

    @Provides
    fun providesPopularMoviesLocalDataSource(dao: PopularMoviesDao): PopularMoviesLocalDataSource {
        return PopularMoviesLocalDataSource(dao)
    }

    @Provides
    fun providesMovieDetailsRemoteDataSource(api: MovieApi): MovieDetailsRemoteDataSource {
        return MovieDetailsRemoteDataSource(api)
    }

    @Provides
    fun providesMovieDetailsLocalDataSource(dao: MovieDetailsDao): MovieDetailsLocalDataSource {
        return MovieDetailsLocalDataSource(dao)
    }
}

package castro.alberto.themovieapp.di

import castro.alberto.themovieapp.data.api.MovieApi
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
    fun providesMovieDetailsRemoteDataSource(api: MovieApi): MovieDetailsRemoteDataSource {
        return MovieDetailsRemoteDataSource(api)
    }
}

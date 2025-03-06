package castro.alberto.themovieapp.di

import castro.alberto.themovieapp.data.datasource.remote.MovieDetailsRemoteDataSource
import castro.alberto.themovieapp.data.datasource.remote.PopularMoviesRemoteDataSource
import castro.alberto.themovieapp.data.repository.MovieDetailsRepository
import castro.alberto.themovieapp.data.repository.PopularMoviesRepository
import castro.alberto.themovieapp.data.repository.implementation.MovieDetailsRepositoryImpl
import castro.alberto.themovieapp.data.repository.implementation.PopularMoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun providesPopularMoviesRepository(
        remoteDataSource: PopularMoviesRemoteDataSource
    ): PopularMoviesRepository {
        return PopularMoviesRepositoryImpl(remoteDataSource)
    }

    @Provides
    fun providesMovieDetailsRepository(
        remoteDataSource: MovieDetailsRemoteDataSource
    ): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(remoteDataSource)
    }
}

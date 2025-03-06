package castro.alberto.themovieapp.di

import castro.alberto.themovieapp.data.repository.MovieDetailsRepository
import castro.alberto.themovieapp.data.repository.PopularMoviesRepository
import castro.alberto.themovieapp.domain.usecase.GetMovieDetailsUseCase
import castro.alberto.themovieapp.domain.usecase.GetPopularMoviesUseCase
import castro.alberto.themovieapp.domain.usecase.implementation.GetMovieDetailsUseCaseImpl
import castro.alberto.themovieapp.domain.usecase.implementation.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun providesGetPopularMoviesUseCase(
        repository: PopularMoviesRepository
    ): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(repository)
    }

    @Provides
    fun providesGetMovieDetailsUseCase(
        repository: MovieDetailsRepository
    ): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(repository)
    }
}

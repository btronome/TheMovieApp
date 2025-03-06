package castro.alberto.themovieapp.domain.usecase.implementation

import castro.alberto.themovieapp.data.repository.MovieDetailsRepository
import castro.alberto.themovieapp.domain.model.MovieDetails
import castro.alberto.themovieapp.domain.usecase.GetMovieDetailsUseCase
import castro.alberto.themovieapp.presentation.exception.toUiMessage
import castro.alberto.themovieapp.presentation.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val repository: MovieDetailsRepository
) : GetMovieDetailsUseCase {
    override fun invoke(movieId: Long): Flow<UiState<MovieDetails>> = flow {
        emit(UiState.Loading)
        repository.getMovieDetails(movieId)
            .onSuccess { movieDetails -> emit(UiState.Success(movieDetails)) }
            .onFailure { exception -> emit(UiState.Error(exception.toUiMessage())) }
    }
}

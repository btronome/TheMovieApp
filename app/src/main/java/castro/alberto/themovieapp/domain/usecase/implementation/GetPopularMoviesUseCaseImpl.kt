package castro.alberto.themovieapp.domain.usecase.implementation

import castro.alberto.themovieapp.data.repository.PopularMoviesRepository
import castro.alberto.themovieapp.domain.model.PopularMovies
import castro.alberto.themovieapp.domain.usecase.GetPopularMoviesUseCase
import castro.alberto.themovieapp.presentation.exception.toUiMessage
import castro.alberto.themovieapp.presentation.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: PopularMoviesRepository
) : GetPopularMoviesUseCase {
    override fun invoke(page: Long): Flow<UiState<PopularMovies>> = flow {
        emit(UiState.Loading)
        repository.getPopularMovies(page)
            .onSuccess { popularMovies -> emit(UiState.Success(popularMovies)) }
            .onFailure { exception -> emit(UiState.Error(exception.toUiMessage())) }
    }
}

package castro.alberto.themovieapp.domain.usecase

import castro.alberto.themovieapp.domain.model.PopularMovies
import castro.alberto.themovieapp.presentation.state.UiState
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesUseCase {
    fun invoke(page: Long): Flow<UiState<PopularMovies>>
}

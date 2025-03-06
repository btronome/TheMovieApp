package castro.alberto.themovieapp.domain.usecase

import castro.alberto.themovieapp.domain.model.MovieDetails
import castro.alberto.themovieapp.presentation.state.UiState
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsUseCase {
    fun invoke(movieId: Long): Flow<UiState<MovieDetails>>
}

package castro.alberto.themovieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castro.alberto.themovieapp.domain.model.MovieDetails
import castro.alberto.themovieapp.domain.usecase.GetMovieDetailsUseCase
import castro.alberto.themovieapp.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<MovieDetails>>(UiState.Loading)
    val uiState: StateFlow<UiState<MovieDetails>> = _uiState.asStateFlow()

    fun fetchMovieDetails(movieId: Long) {
        viewModelScope.launch {
            getMovieDetailsUseCase.invoke(movieId).collectLatest { uiState ->
                _uiState.value = uiState
            }
        }
    }
}

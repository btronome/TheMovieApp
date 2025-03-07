package castro.alberto.themovieapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import castro.alberto.themovieapp.domain.model.Movie
import castro.alberto.themovieapp.domain.model.PopularMovies
import castro.alberto.themovieapp.domain.usecase.GetPopularMoviesUseCase
import castro.alberto.themovieapp.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<PopularMovies>>(UiState.Loading)
    val uiState: StateFlow<UiState<PopularMovies>> = _uiState.asStateFlow()
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    private val movies: StateFlow<List<Movie>> = _movies.asStateFlow()
    private val _filteredMovies = MutableStateFlow<List<Movie>>(emptyList())
    private val filteredMovies: StateFlow<List<Movie>> = _filteredMovies.asStateFlow()
    private var currentPage = 1L
    private var isLastPage = false

    val displayedMovies: StateFlow<List<Movie>> = combine(
        movies, filteredMovies, searchQuery
    ) { movieList, filteredList, query ->
        if (query.isBlank()) movieList else filteredList
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies() {
        if (isLastPage) return

        viewModelScope.launch {
            getPopularMoviesUseCase.invoke(currentPage).collectLatest { uiState ->
                _uiState.value = uiState
            }
        }
    }

    fun handlePaging(movies: List<Movie>) {
        val updatedList = (_movies.value + movies).distinctBy { it.id }
        _movies.value = updatedList

        if (movies.isNotEmpty()) {
            currentPage++
        } else {
            isLastPage = true
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        _filteredMovies.value = if (query.isBlank()) {
            emptyList()
        } else {
            _movies.value.filter { it.title.contains(query, ignoreCase = true) }
        }
    }
}

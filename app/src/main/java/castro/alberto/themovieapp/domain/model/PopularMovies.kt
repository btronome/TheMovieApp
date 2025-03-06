package castro.alberto.themovieapp.domain.model

data class PopularMovies(
    val page: Long,
    val results: List<Movie>,
    val totalPages: Long,
    val totalResults: Long
)

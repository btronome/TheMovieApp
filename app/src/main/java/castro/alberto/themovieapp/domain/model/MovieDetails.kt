package castro.alberto.themovieapp.domain.model

data class MovieDetails(
    val genres: List<Genre>,
    val id: Long,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String
)

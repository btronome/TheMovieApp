package castro.alberto.themovieapp.data.mapper.remote

import castro.alberto.themovieapp.data.model.remote.MovieDetailsResponse
import castro.alberto.themovieapp.domain.model.MovieDetails

fun MovieDetailsResponse.toDomainModel(): MovieDetails {
    return MovieDetails(
        genres = this.genres.toDomainModel(),
        id = this.id,
        originalTitle = this.originalTitle,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title
    )
}

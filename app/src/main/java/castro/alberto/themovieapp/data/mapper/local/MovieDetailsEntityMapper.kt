package castro.alberto.themovieapp.data.mapper.local

import castro.alberto.themovieapp.data.model.local.MovieDetailsEntity
import castro.alberto.themovieapp.domain.model.MovieDetails

fun MovieDetails.toEntityModel(): MovieDetailsEntity {
    return MovieDetailsEntity(
        genres = this.genres.toEntityModel(),
        id = this.id,
        originalTitle = this.originalTitle,
        overview = this.overview,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title
    )
}

fun MovieDetailsEntity?.toDomainModel(): MovieDetails? {
    return this?.let {
        MovieDetails(
            genres = this.genres.toDomainModel(),
            id = this.id,
            originalTitle = this.originalTitle,
            overview = this.overview,
            posterPath = this.posterPath,
            releaseDate = this.releaseDate,
            title = this.title
        )
    } ?: run { null }
}
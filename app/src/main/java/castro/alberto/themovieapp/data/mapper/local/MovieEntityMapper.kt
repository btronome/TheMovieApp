package castro.alberto.themovieapp.data.mapper.local

import castro.alberto.themovieapp.data.model.local.MovieEntity
import castro.alberto.themovieapp.domain.model.Movie

fun Movie.toEntityModel(): MovieEntity {
    return MovieEntity(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun List<Movie>.toEntityModel(): List<MovieEntity> {
    return this.map { movie -> movie.toEntityModel() }
}

fun MovieEntity.toDomainModel(): Movie {
    return Movie(
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        id = this.id,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun List<MovieEntity>.toDomainModel(): List<Movie> {
    return this.map { movieEntity -> movieEntity.toDomainModel() }
}

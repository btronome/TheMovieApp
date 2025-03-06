package castro.alberto.themovieapp.data.mapper.remote

import castro.alberto.themovieapp.data.model.remote.MovieResponse
import castro.alberto.themovieapp.domain.model.Movie

fun MovieResponse.toDomainModel(): Movie {
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

fun List<MovieResponse>.toDomainModel(): List<Movie> {
    return this.map { movieResponse -> movieResponse.toDomainModel() }
}

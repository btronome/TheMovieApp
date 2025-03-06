package castro.alberto.themovieapp.data.mapper.local

import castro.alberto.themovieapp.data.model.local.PopularMoviesEntity
import castro.alberto.themovieapp.domain.model.PopularMovies

fun PopularMovies.toEntityModel(): PopularMoviesEntity {
    return PopularMoviesEntity(
        page = this.page,
        results = this.results.toEntityModel(),
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

fun PopularMoviesEntity?.toDomainModel(): PopularMovies? {
    return this?.let {
        PopularMovies(
            page = this.page,
            results = this.results.toDomainModel(),
            totalPages = this.totalPages,
            totalResults = this.totalResults
        )
    } ?: run { null }
}

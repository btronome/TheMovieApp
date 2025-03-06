package castro.alberto.themovieapp.data.mapper.remote

import castro.alberto.themovieapp.data.model.remote.PopularMoviesResponse
import castro.alberto.themovieapp.domain.model.PopularMovies

fun PopularMoviesResponse.toDomainModel(): PopularMovies {
    return PopularMovies(
        page = this.page,
        results = this.results.toDomainModel(),
        totalPages = this.totalPages,
        totalResults = this.totalResults
    )
}

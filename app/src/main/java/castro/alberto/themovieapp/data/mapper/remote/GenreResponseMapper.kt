package castro.alberto.themovieapp.data.mapper.remote

import castro.alberto.themovieapp.data.model.remote.GenreResponse
import castro.alberto.themovieapp.domain.model.Genre

fun GenreResponse.toDomainModel(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun List<GenreResponse>.toDomainModel(): List<Genre> {
    return this.map { genreResponse -> genreResponse.toDomainModel() }
}

package castro.alberto.themovieapp.data.mapper.local

import castro.alberto.themovieapp.data.model.local.GenreEntity
import castro.alberto.themovieapp.domain.model.Genre

fun Genre.toEntityModel(): GenreEntity {
    return GenreEntity(
        id = this.id,
        name = this.name
    )
}

fun List<Genre>.toEntityModel(): List<GenreEntity> {
    return this.map { genre -> genre.toEntityModel() }
}

fun GenreEntity.toDomainModel(): Genre {
    return Genre(
        id = this.id,
        name = this.name
    )
}

fun List<GenreEntity>.toDomainModel(): List<Genre> {
    return this.map { genreEntity -> genreEntity.toDomainModel() }
}

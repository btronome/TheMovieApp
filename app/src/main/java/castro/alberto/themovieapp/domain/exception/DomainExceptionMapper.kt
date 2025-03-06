package castro.alberto.themovieapp.domain.exception

import castro.alberto.themovieapp.data.exception.DataException

fun DataException.mapToDomainException(): DomainException {
    return when (this) {
        is DataException.NetworkError -> DomainException.NetworkError(
            cause = this.cause
        )
        is DataException.ServerError -> DomainException.ServerError(
            code = this.code,
            message = this.message
        )
        else -> DomainException.UnknownError(
            cause = this.cause
        )
    }
}

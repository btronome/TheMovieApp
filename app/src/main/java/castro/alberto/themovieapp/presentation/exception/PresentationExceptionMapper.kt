package castro.alberto.themovieapp.presentation.exception

import castro.alberto.themovieapp.domain.exception.DomainException

fun Throwable.toUiMessage(): String {
    return when (this) {
        is DomainException.NetworkError -> "No internet connection. Please try again."
        is DomainException.ServerError -> "Server error (${this.code}): ${this.message}"
        is DomainException.UnknownError -> "An unknown error occurred."
        else -> "Something went wrong. Please try again later."
    }
}

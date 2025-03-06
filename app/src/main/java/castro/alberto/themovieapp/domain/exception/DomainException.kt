package castro.alberto.themovieapp.domain.exception

sealed class DomainException : Throwable() {
    data class NetworkError(override val cause: Throwable?) : DomainException()
    data class ServerError(val code: Int, override val message: String) : DomainException()
    data class UnknownError(override val cause: Throwable?) : DomainException()
}

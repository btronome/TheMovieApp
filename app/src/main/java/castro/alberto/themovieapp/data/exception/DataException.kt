package castro.alberto.themovieapp.data.exception

import java.io.IOException

sealed class DataException : IOException() {
    data class NetworkError(override val cause: Throwable? = null) : DataException()
    data class ServerError(val code: Int, override val message: String) : DataException()
    data class DataBaseError(override val cause: Throwable?) : DataException()
    data class UnknownError(override val cause: Throwable? = null) : DataException()
}

package castro.alberto.themovieapp.data.exception

import android.database.SQLException
import retrofit2.HttpException
import java.io.IOException

fun Throwable.mapToDataException(): DataException {
    return when (this) {
        is IOException -> DataException.NetworkError(
            cause = this.cause
        )
        is HttpException -> DataException.ServerError(
            code = this.code(),
            message = this.message()
        )
        is SQLException -> DataException.DataBaseError(
            cause = this.cause
        )
        else -> DataException.UnknownError(
            cause = this.cause
        )
    }
}

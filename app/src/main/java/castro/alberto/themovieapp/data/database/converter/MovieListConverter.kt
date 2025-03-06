package castro.alberto.themovieapp.data.database.converter

import androidx.room.TypeConverter
import castro.alberto.themovieapp.data.model.local.MovieEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromListToString(data: List<MovieEntity>?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<MovieEntity>? {
        val data = object : TypeToken<List<MovieEntity>>() {}.type
        return gson.fromJson(value, data)
    }
}

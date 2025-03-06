package castro.alberto.themovieapp.data.database.converter

import androidx.room.TypeConverter
import castro.alberto.themovieapp.data.model.local.GenreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromListToString(data: List<GenreEntity>?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<GenreEntity>? {
        val data = object : TypeToken<List<GenreEntity>>() {}.type
        return gson.fromJson(value, data)
    }
}

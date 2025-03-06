package castro.alberto.themovieapp.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LongListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromListToString(data: List<Long>?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<Long>? {
        val data = object : TypeToken<List<Long>>() {}.type
        return gson.fromJson(value, data)
    }
}

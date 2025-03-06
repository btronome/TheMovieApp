package castro.alberto.themovieapp.data.model.remote

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    val genres: List<GenreResponse>,
    val id: Long,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String
)

package castro.alberto.themovieapp.data.model.remote

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    val page: Long,
    val results: List<MovieResponse>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long
)

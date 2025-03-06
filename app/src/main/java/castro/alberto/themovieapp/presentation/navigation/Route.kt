package castro.alberto.themovieapp.presentation.navigation

sealed class Route(val route: String) {
    data object PopularMovies : Route(route = "popular_movies")
    data object MovieDetails : Route(route = "movie_details/{movie_id}") {
        fun route(movieId: Long) = "movie_details/$movieId"
    }
}

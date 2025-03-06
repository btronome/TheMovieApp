package castro.alberto.themovieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import castro.alberto.themovieapp.presentation.ui.MovieDetailsScreen
import castro.alberto.themovieapp.presentation.ui.PopularMoviesScreen

@Composable
fun MainNavGraph(
    onTitleChanged: (String) -> Unit,
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.PopularMovies.route
    ) {
        composable(route = Route.PopularMovies.route) {
            PopularMoviesScreen(
                onTitleChanged = { title ->
                    onTitleChanged(title)
                },
                onMovieClicked = { movieId ->
                    navController.navigate(Route.MovieDetails.route(movieId))
                },
                modifier = modifier
            )
        }
        composable(
            route = Route.MovieDetails.route,
            arguments = listOf(
                navArgument("movie_id") {
                    type = NavType.LongType
                }
            )
        ) { entry ->
            val movieId = entry.arguments?.getLong("movie_id") ?: 0L
            MovieDetailsScreen(
                onTitleChanged = { title ->
                    onTitleChanged(title)
                },
                movieId = movieId,
                modifier = modifier
            )
        }
    }
}

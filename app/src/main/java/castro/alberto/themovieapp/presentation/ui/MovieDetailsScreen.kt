package castro.alberto.themovieapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import castro.alberto.themovieapp.R
import castro.alberto.themovieapp.domain.model.MovieDetails
import castro.alberto.themovieapp.presentation.components.GlideImage
import castro.alberto.themovieapp.presentation.components.Loader
import castro.alberto.themovieapp.presentation.state.UiState
import castro.alberto.themovieapp.presentation.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    onTitleChanged: (String) -> Unit,
    movieId: Long,
    modifier: Modifier,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.fetchMovieDetails(movieId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {

    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when (state) {
            UiState.Loading -> {
                Loader()
            }

            is UiState.Success -> {
                MovieDetails(
                    onTitleChanged = { title ->
                        onTitleChanged(title)
                    },
                    state = state
                )
            }

            is UiState.Error -> {
                val context = LocalContext.current
                val message = (state as UiState.Error).message
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
private fun MovieDetails(onTitleChanged: (String) -> Unit, state: UiState<MovieDetails>) {
    val movie = (state as UiState.Success).data
    onTitleChanged(movie.title)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            shape = RoundedCornerShape(
                bottomStart = dimensionResource(R.dimen.dim_16),
                bottomEnd = dimensionResource(R.dimen.dim_16)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.dim_4))
        ) {
            GlideImage(
                imageUrl = movie.posterPath,
                contentDescription = movie.originalTitle,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.dim_8))
        ) {
            items(movie.genres) { genre ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(dimensionResource(R.dimen.dim_12)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.dim_4))
                ) {
                    Text(
                        text = genre.name,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(
                            vertical = dimensionResource(R.dimen.dim_4),
                            horizontal = dimensionResource(R.dimen.dim_8)
                        )
                    )
                }
            }
        }
        Text(
            text = movie.overview,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dim_8))
        )
        Text(
            text = movie.releaseDate,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dim_8))
        )
    }
}

package castro.alberto.themovieapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import castro.alberto.themovieapp.R
import castro.alberto.themovieapp.domain.model.Movie
import castro.alberto.themovieapp.presentation.components.GlideImage
import castro.alberto.themovieapp.presentation.components.Loader
import castro.alberto.themovieapp.presentation.state.UiState
import castro.alberto.themovieapp.presentation.viewmodel.PopularMoviesViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun PopularMoviesScreen(
    onTitleChanged: (String) -> Unit,
    onMovieClicked: (Long) -> Unit,
    modifier: Modifier,
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    onTitleChanged(stringResource(R.string.app_name))
    val state by viewModel.uiState.collectAsState()
    val movies by viewModel.movies.collectAsState()
    var showMoreMoviesButton by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(top = dimensionResource(R.dimen.dim_8))
            .fillMaxSize()
    ) {
        when (state) {
            UiState.Loading -> {
                Loader()
            }

            is UiState.Success -> {
                val data = (state as UiState.Success).data.results
                viewModel.handlePaging(data)
                MoviesList(
                    movies = movies,
                    onMovieClicked = { movieId ->
                        onMovieClicked(movieId)
                    },
                    showMoreMoviesButton = {
                        showMoreMoviesButton = it
                    }
                )
            }

            is UiState.Error -> {
                val context = LocalContext.current
                val message = (state as UiState.Error).message
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }

        if (showMoreMoviesButton) {
            ExtendedFloatingActionButton(
                onClick = { viewModel.fetchPopularMovies() },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = dimensionResource(R.dimen.dim_16)),
                icon = {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(R.string.discover_more_movies)
                    )
                },
                text = {
                    Text(text = stringResource(R.string.discover_more_movies))
                },
                containerColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Composable
private fun MoviesList(
    movies: List<Movie>,
    onMovieClicked: (Long) -> Unit,
    showMoreMoviesButton: (Boolean) -> Unit
) {
    val gridState = rememberLazyGridState()
    LaunchedEffect(movies) {
        if (movies.size > pageSize) {
            gridState.scrollToItem(index = movies.size - pageSize)
        }
    }

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .map { visibleItems ->
                val totalItems = gridState.layoutInfo.totalItemsCount
                val lastVisibleItem = visibleItems.lastOrNull()?.index ?: 0
                lastVisibleItem >= totalItems - 1
            }
            .distinctUntilChanged()
            .collect { isAtEnd ->
                showMoreMoviesButton(isAtEnd)
            }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(gridCellsCount),
        modifier = Modifier.fillMaxSize(),
        state = gridState,
        contentPadding = PaddingValues(dimensionResource(R.dimen.dim_8)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dim_8)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dim_8))
    ) {
        items(movies) { movie ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(dimensionResource(R.dimen.dim_12)),
                elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.dim_4)),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(imageRatio)
                    .padding(dimensionResource(R.dimen.dim_4))
                    .clickable { onMovieClicked(movie.id) }
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Card(
                        shape = RoundedCornerShape(
                            topStart = dimensionResource(R.dimen.dim_12),
                            topEnd = dimensionResource(R.dimen.dim_12)
                        ),
                        modifier = Modifier
                            .weight(cardWeight)
                            .fillMaxWidth()
                    ) {
                        GlideImage(
                            imageUrl = movie.posterPath,
                            contentDescription = movie.originalTitle,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Text(
                        text = movie.title,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.dim_4))
                    )
                }
            }
        }
    }
}

private const val pageSize = 20
private const val gridCellsCount = 3
private const val cardWeight = 1f
private const val imageRatio = 0.5f

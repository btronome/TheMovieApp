package castro.alberto.themovieapp.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import castro.alberto.themovieapp.BuildConfig
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage as GlideImageCompose

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GlideImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    GlideImageCompose(
        model = "${BuildConfig.MOVIE_IMAGE_URL}$imageUrl",
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}

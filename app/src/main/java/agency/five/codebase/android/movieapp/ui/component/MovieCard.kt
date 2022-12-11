package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieCardViewState(
    val imageUrl:String,
    val movieId: Int,
    val isFavorite:Boolean
)

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movieCardViewState: MovieCardViewState,
    onClick: () -> Unit,
    onFavoriteButtonClicked:()->Unit
) {
    Card(
        modifier=modifier
            .clickable(onClick = onClick),
        elevation = dimensionResource(id = R.dimen.elevation)
    ) {
        AsyncImage(
            model = movieCardViewState.imageUrl,
            contentDescription = "actor image",
            contentScale = ContentScale.FillWidth
        )
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = modifier
        ){
            FavoriteButton(
                isFavorite = movieCardViewState.isFavorite,
                onClick = { onFavoriteButtonClicked() }
            )
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movie = MoviesMock.getMoviesList()[4]
    val movieCardViewState =
        MovieCardViewState(imageUrl = movie.imageUrl!!, movieId = movie.id, movie.isFavorite)
    MovieCard(
        movieCardViewState = movieCardViewState,
        modifier = Modifier
            .width(125.dp)
            .height(205.dp)
            .padding(MaterialTheme.spacing.small),
        onClick = { },
        onFavoriteButtonClicked = {}
    )
}

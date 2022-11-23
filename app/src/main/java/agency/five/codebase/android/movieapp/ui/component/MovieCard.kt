package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

data class MovieCardViewState(
    val imageUrl:String,
    val title:String,
    val isFavorite:Boolean
)

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    movieCardViewState: MovieCardViewState,
    onFavoriteButtonClicked: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier=modifier
            .clickable { onClick() },
        elevation = dimensionResource(id = R.dimen.elevation)
    ) {
        Box(
            modifier = modifier
        ){
            AsyncImage(
                model = movieCardViewState.imageUrl,
                contentDescription = "actor image",
            )
            FavoriteButton(
                isFavorite = movieCardViewState.isFavorite,
                onClick = { onFavoriteButtonClicked },
                modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    val movie = MoviesMock.getMoviesList()[4]
    val movieCardViewState =
        MovieCardViewState(imageUrl = movie.imageUrl!!, title = movie.title, movie.isFavorite)
    MovieCard(
        movieCardViewState = movieCardViewState,
        modifier = Modifier
            .width(125.dp)
            .height(205.dp)
            .padding(10.dp),
        onClick = { },
        onFavoriteButtonClicked = { }
    )
}

//should I add it in manifest?
//2022-11-23 18:23:13.221 18848-18917/agency.five.codebase.android.movieapp E/AndroidRuntime: FATAL EXCEPTION: OkHttp Dispatcher
//    Process: agency.five.codebase.android.movieapp, PID: 18848
//    java.lang.SecurityException: Permission denied (missing INTERNET permission?)
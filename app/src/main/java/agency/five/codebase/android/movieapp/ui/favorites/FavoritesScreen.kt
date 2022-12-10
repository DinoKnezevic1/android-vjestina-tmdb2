package agency.five.codebase.android.movieapp.ui.favorites

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapper
import agency.five.codebase.android.movieapp.ui.favorites.mapper.FavoritesMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.Blue
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

const val NUMBER_OF_COLUMNS = 3
private val favoritesScreenMapper: FavoritesMapper = FavoritesMapperImpl()

val favoritesScreenViewState =
    favoritesScreenMapper.toFavoritesViewState(MoviesMock.getMoviesList())


@Composable
fun FavoritesScreen(
    favoritesViewState: FavoritesViewState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(NUMBER_OF_COLUMNS),
        contentPadding = PaddingValues(
            horizontal = MaterialTheme.spacing.small,
            vertical = MaterialTheme.spacing.medium
        ),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
    ) {
        item(
            span = { GridItemSpan(maxLineSpan) }
        ) {
            Text(

                text = stringResource(id = R.string.favorites),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Blue,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }
        items(
            favoritesViewState.movies.size,
            key = { movie -> favoritesViewState.movies[movie].movieCardViewState.movieId }
        ) { movie ->
            MovieCard(
                movieCardViewState = favoritesViewState.movies[movie].movieCardViewState,
                modifier = Modifier
                    .padding(
                        horizontal = MaterialTheme.spacing.extraSmall,
                        vertical = MaterialTheme.spacing.small
                    ),
                onClick = { }
            )
        }
    }
}

@Preview
@Composable
fun FavoritesScreenPreview() {
    MovieAppTheme {
        FavoritesScreen(
            favoritesViewState = favoritesScreenViewState
        )
    }
}
    
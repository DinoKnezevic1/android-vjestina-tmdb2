package agency.five.codebase.android.movieapp.ui.home

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCard
import agency.five.codebase.android.movieapp.ui.component.MovieCardViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabel
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapper
import agency.five.codebase.android.movieapp.ui.home.mapper.HomeScreenMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.MovieAppTheme
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

private val homeScreenMapper: HomeScreenMapper = HomeScreenMapperImpl()

val popularCategoryLabels = listOf(
    MovieCategory.POPULAR_STREAMING,
    MovieCategory.POPULAR_ONTV,
    MovieCategory.POPULAR_FORRENT,
    MovieCategory.POPULAR_INTHEATERS
)

val nowPlayingCategoryLabels = listOf(
    MovieCategory.NOWPLAYING_MOVIES,
    MovieCategory.NOWPLAYING_TV
)

val upcomingCategoryLabels = listOf(
    MovieCategory.UPCOMING_TODAY,
    MovieCategory.UPCOMING_THISWEEK
)

val popularCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    popularCategoryLabels,
    MovieCategory.POPULAR_STREAMING,
    MoviesMock.getMoviesList()
)
val nowPlayingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    nowPlayingCategoryLabels,
    MovieCategory.NOWPLAYING_MOVIES,
    MoviesMock.getMoviesList()
)
val upcomingCategoryViewState = homeScreenMapper.toHomeMovieCategoryViewState(
    upcomingCategoryLabels,
    MovieCategory.UPCOMING_TODAY,
    MoviesMock.getMoviesList()
)


@Composable
fun HomeScreen(
    popularCategoryViewState: HomeMovieCategoryViewState,
    nowPlayingCategoryViewState: HomeMovieCategoryViewState,
    upcomingCategoryViewState: HomeMovieCategoryViewState,
    onFavoriteButtonClicked: () -> Unit,
    onPopularLabelClick: (Int) -> Unit,
    onNowPlayingLabelClick: (Int) -> Unit,
    onUpcomingLabelClick: (Int) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = Modifier
    ) {
        item {
            HomeScreenCategory(
                popularCategoryViewState,
                stringResource(id = R.string.popularTitle),
                onPopularLabelClick,
                onFavoriteButtonClicked
            )
        }

        item {
            HomeScreenCategory(
                nowPlayingCategoryViewState,
                stringResource(id = R.string.nowPlayingTitle),
                onNowPlayingLabelClick,
                onFavoriteButtonClicked
            )
        }

        item {
            HomeScreenCategory(
                upcomingCategoryViewState,
                stringResource(id = R.string.upcomingTitle),
                onUpcomingLabelClick,
                onFavoriteButtonClicked
            )
        }
    }
}

@Composable
fun HomeScreenCategory(
    homeMovieCategoryViewState: HomeMovieCategoryViewState,
    title: String,
    onLabelClick: (Int) -> Unit,
    onFavoriteButtonClicked: () -> Unit
) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(
                start = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.medium
            )
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        userScrollEnabled = false,
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.small),
        contentPadding = PaddingValues(start=MaterialTheme.spacing.medium)
    ) {
        items(homeMovieCategoryViewState.movieCategories.size) { category ->
            val movieCategoryLabelViewState = homeMovieCategoryViewState.movieCategories[category]
            MovieCategoryLabel(
                movieCategoryLabelViewState = movieCategoryLabelViewState,
                onClick = { onLabelClick(movieCategoryLabelViewState.itemId) }
            )
        }
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.small),
        contentPadding = PaddingValues(start=MaterialTheme.spacing.medium)
    ) {
        items(homeMovieCategoryViewState.movies.size) { movie ->
            val movieState = homeMovieCategoryViewState.movies[movie]
            MovieCard(
                movieCardViewState = MovieCardViewState(
                    movieId = movieState.movieId,
                    imageUrl = movieState.imageUrl,
                    isFavorite = movieState.isFavorite
                ),
                onClick = {onFavoriteButtonClicked()}
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MovieAppTheme {
        HomeScreen(
            popularCategoryViewState,
            nowPlayingCategoryViewState,
            upcomingCategoryViewState,
            onFavoriteButtonClicked = {},
            onPopularLabelClick = {},
            onNowPlayingLabelClick = {},
            onUpcomingLabelClick = {}
        )
    }
}

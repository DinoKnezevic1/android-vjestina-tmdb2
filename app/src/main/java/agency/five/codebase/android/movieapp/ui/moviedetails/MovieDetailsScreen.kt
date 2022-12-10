package agency.five.codebase.android.movieapp.ui.moviedetails

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.component.ActorCard
import agency.five.codebase.android.movieapp.ui.component.CircularProgressBar
import agency.five.codebase.android.movieapp.ui.component.CrewItem
import agency.five.codebase.android.movieapp.ui.component.FavoriteButton
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapper
import agency.five.codebase.android.movieapp.ui.moviedetails.mapper.MovieDetailsMapperImpl
import agency.five.codebase.android.movieapp.ui.theme.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

private val movieDetailsScreenMapper: MovieDetailsMapper = MovieDetailsMapperImpl()

val movieDetailsViewState =
    movieDetailsScreenMapper.toMovieDetailsViewState(MoviesMock.getMovieDetails())

const val NUMBER_OF_CELLS = 3

@Composable
fun MovieDetailsScreen(
    movieDetailsViewState: MovieDetailsViewState,
) {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .background(Gray100)
    ) {
        MovieImage(movieDetailsViewState = movieDetailsViewState)
        MovieOverview(movieDetailsViewState = movieDetailsViewState)
        MovieCrew(movieDetailsViewState = movieDetailsViewState)
        MovieCast(movieDetailsViewState = movieDetailsViewState)
    }
}

@Composable
fun MovieImage(
    movieDetailsViewState: MovieDetailsViewState
) {
    Box(
        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            model = movieDetailsViewState.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .fillMaxSize()
        )
        Column(
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircularProgressBar(
                    score = movieDetailsViewState.voteAverage
                )
                Text(
                    text = stringResource(id = R.string.userScore),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.secondary
                )
            }
            Text(
                text = movieDetailsViewState.title,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colors.secondary
            )
            FavoriteButton(
                isFavorite = movieDetailsViewState.isFavorite,
                onClick = { },
                modifier = Modifier
                    .padding(bottom = MaterialTheme.spacing.medium)
            )
        }
    }
}

@Composable
fun MovieOverview(
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = R.string.overview),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = movieDetailsViewState.overview,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

@Composable
fun MovieCrew(
    movieDetailsViewState: MovieDetailsViewState
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(NUMBER_OF_CELLS),
        contentPadding = PaddingValues(MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        userScrollEnabled = false,
        modifier = Modifier
            .height(150.dp)
    ) {
        items(
            movieDetailsViewState.crew.size
        ) { crewman ->
            CrewItem(
                crewItemViewState = movieDetailsViewState.crew[crewman].crewItemViewState,
                modifier = Modifier
                    .padding(PaddingValues(MaterialTheme.spacing.small))
            )
        }
    }
}

@Composable
fun MovieCast(
    movieDetailsViewState: MovieDetailsViewState
) {
    Column(
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(id = R.string.topBilledCast),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(bottom = MaterialTheme.spacing.small)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            items(
                movieDetailsViewState.cast.size
            ) { actor ->
                ActorCard(
                    actorCardViewState = movieDetailsViewState.cast[actor].actorCardViewState,
                    modifier = Modifier
                        .width(125.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MovieDetailsScreenPreview() {
    MovieAppTheme {
        MovieDetailsScreen(
            movieDetailsViewState = movieDetailsViewState
        )
    }
}

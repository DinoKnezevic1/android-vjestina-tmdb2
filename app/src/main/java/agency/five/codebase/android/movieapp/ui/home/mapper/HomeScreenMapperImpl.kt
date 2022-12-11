package agency.five.codebase.android.movieapp.ui.home.mapper

import agency.five.codebase.android.movieapp.R
import agency.five.codebase.android.movieapp.model.Movie
import agency.five.codebase.android.movieapp.model.MovieCategory
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelTextViewState
import agency.five.codebase.android.movieapp.ui.component.MovieCategoryLabelViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieCategoryViewState
import agency.five.codebase.android.movieapp.ui.home.HomeMovieViewState

class HomeScreenMapperImpl : HomeScreenMapper {
    override fun toHomeMovieCategoryViewState(
        movieCategories: List<MovieCategory>,
        selectedMovieCategory: MovieCategory,
        movies: List<Movie>
    ): HomeMovieCategoryViewState {

        return HomeMovieCategoryViewState(
            movieCategories.map {
                MovieCategoryLabelViewState(
                    itemId = it.ordinal,
                    isSelected = selectedMovieCategory == it,
                    categoryText = MovieCategoryLabelTextViewState.ResourceText(
                        getResourceTextIdFromMovieCategory(it)
                    )
                )
            },
            movies.map {
                HomeMovieViewState(
                    movieId = it.id,
                    imageUrl = it.imageUrl!!,
                    isFavorite = it.isFavorite
                )
            }
        )
    }
}

fun getResourceTextIdFromMovieCategory(
    movieCategory: MovieCategory
): Int {
    return when (movieCategory) {
        MovieCategory.POPULAR_STREAMING -> R.string.streaming
        MovieCategory.POPULAR_ON_TV -> R.string.on_tv
        MovieCategory.POPULAR_FOR_RENT -> R.string.for_rent
        MovieCategory.POPULAR_IN_THEATERS -> R.string.in_theaters
        MovieCategory.NOW_PLAYING_MOVIES -> R.string.movies
        MovieCategory.NOW_PLAYING_TV -> R.string.TV
        MovieCategory.UPCOMING_TODAY -> R.string.today
        MovieCategory.UPCOMING_THIS_WEEK -> R.string.this_week
    }
}

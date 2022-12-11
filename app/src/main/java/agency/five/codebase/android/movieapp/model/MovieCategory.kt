package agency.five.codebase.android.movieapp.model

enum class MovieCategory {
    POPULAR_STREAMING,
    POPULAR_ONTV,
    POPULAR_FORRENT,
    POPULAR_INTHEATERS,
    NOWPLAYING_MOVIES,
    NOWPLAYING_TV,
    UPCOMING_TODAY,
    UPCOMING_THISWEEK;

    companion object {
        fun getByOrdinal(ordinal: Int) = values().first { it.ordinal == ordinal }
    }
}

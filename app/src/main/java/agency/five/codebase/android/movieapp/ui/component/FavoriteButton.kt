package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FavoriteButton(
     isFavorite:Boolean,
     onClick: ()->Unit,
     modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = if(isFavorite) R.drawable.favoritefull else R.drawable.favoriteempty),
        contentDescription = "Favorite button",
        colorFilter = ColorFilter.tint(Color.White),
        modifier = modifier
            .size(dimensionResource(id = R.dimen.favorite_button_size))
            .clip(CircleShape)
            .padding(dimensionResource(id = R.dimen.small_spacing))
            .clickable {
                //!isFavorite
                onClick(isFavorite)
                //onClick()={!isFavorite} I get an error saying I can't change val parameters
            }
    )
}

fun onClick(isFavorite: Boolean) {
    !isFavorite
}

@Preview
@Composable
private fun FavoriteButtonPreview(){
    FavoriteButton(isFavorite = false, onClick = { /*TODO*/ })
}



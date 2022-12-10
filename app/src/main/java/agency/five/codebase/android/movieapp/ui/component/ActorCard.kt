package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import agency.five.codebase.android.movieapp.ui.theme.GrayText
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ActorCardViewState(
    val imageUrl: String,
    val name: String,
    val character: String
)

@Composable
fun ActorCard(
    actorCardViewState: ActorCardViewState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        backgroundColor = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = "actor image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxHeight()
            )
            Text(
                text = actorCardViewState.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .padding(MaterialTheme.spacing.extraSmall)
            )
            Text(
                text = actorCardViewState.character,
                fontSize = 13.sp,
                color = GrayText,
                modifier = modifier
                    .padding(MaterialTheme.spacing.extraSmall)
            )
        }
        Spacer(
            modifier = Modifier
                .size(MaterialTheme.spacing.extraSmall)
        )
    }
}

@Preview
@Composable
private fun ActorCardPreview() {
    val actor = MoviesMock.getActor()
    val actorCardViewState = ActorCardViewState(
        actor.imageUrl.toString(),
        actor.name,
        actor.character
    )
    ActorCard(actorCardViewState)
}

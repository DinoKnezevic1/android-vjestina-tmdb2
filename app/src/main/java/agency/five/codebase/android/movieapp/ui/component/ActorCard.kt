package agency.five.codebase.android.movieapp.ui.component
import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class ActorCardViewState(
    val imageUrl:String,
    val name: String,
    val character: String
)

@Composable
fun ActorCard (
    actorCardViewState: ActorCardViewState,
    modifier:Modifier = Modifier
) {
    Card {
        Column(

            modifier = modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = actorCardViewState.imageUrl,
                contentDescription = "actor image",
                modifier = modifier
                    .width(125.dp)
                    .height(170.dp)
            )
            Text(
                text = actorCardViewState.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .width(100.dp)
                    .padding(start = 5.dp, end = 2.dp, top = 3.dp)
            )
            Text(
                text = actorCardViewState.character,
                fontSize = 13.sp,
                color = Color(0xFF828282),
                modifier = modifier
                    .width(100.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .size(5.dp)
        )
    }
}

@Preview
@Composable
private fun ActorCardPreview(){
    val actor = MoviesMock.getActor()
    val actorCardViewState=ActorCardViewState(actor.imageUrl.toString(),actor.name,actor.character)
    ActorCard(actorCardViewState)
}
package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.mock.MoviesMock
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

data class CrewItemViewState(
    val name: String,
    val job: String
)

@Composable
fun CrewItem(
    crewItemViewState: CrewItemViewState
) {
    Column {
        Text(
            text = crewItemViewState.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = crewItemViewState.job,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview
@Composable
fun CrewItemPreview(){
    val crewman = MoviesMock.getCrewman()
    val crewItemViewState = CrewItemViewState(crewman.name,crewman.job)
    CrewItem(crewItemViewState)
}
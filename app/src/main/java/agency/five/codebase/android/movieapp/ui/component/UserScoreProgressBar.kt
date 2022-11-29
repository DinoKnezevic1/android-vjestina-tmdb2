package agency.five.codebase.android.movieapp.ui.component

import agency.five.codebase.android.movieapp.ui.theme.GreenProgressBar
import agency.five.codebase.android.movieapp.ui.theme.GreenProgressBarBackground
import agency.five.codebase.android.movieapp.ui.theme.spacing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

private const val animationDuration = 650

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    score: Double,
) {
    val animationPlayed = remember { mutableStateOf(false) }
    val currentPercentage = animateFloatAsState(
        targetValue = (if (animationPlayed.value) score.toFloat() else 0f),
        animationSpec = tween(
            durationMillis = animationDuration
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed.value = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(50.dp)
    ) {
        Canvas(
            modifier = Modifier
                .padding(MaterialTheme.spacing.extraSmall)
                .size(60.dp)
        ) {
            drawArc(
                startAngle = -90f,
                sweepAngle = 360f,
                color = GreenProgressBarBackground,
                useCenter = false,
                style = Stroke(5.dp.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                color = GreenProgressBar,
                useCenter = false,
                style = Stroke(5.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            text = (score * 10).toString(),
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CircularProgressBarPreview() {
    CircularProgressBar(score = 0.75)
}

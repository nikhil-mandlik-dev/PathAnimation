package com.nikhil.here.pathanimation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.nikhil.here.pathanimation.ui.theme.Red200
import com.nikhil.here.pathanimation.ui.theme.Red400
import kotlin.math.roundToInt


@Composable
fun RewardItem(
    modifier: Modifier = Modifier, rewardAnimatable: Animatable<Float, AnimationVector1D>,
    updateSourceOffset: (Offset, IntSize) -> Unit
) {
    Box(
        modifier = Modifier.onGloballyPositioned {
            updateSourceOffset(it.positionInRoot(), it.size)
        },
        contentAlignment = Alignment.Center,
    ) {
        LinearProgressIndicator(
            modifier = modifier
                 .rotate(-90f)
                .size(54.dp)
                .clip(RoundedCornerShape(16.dp)),
            trackColor = Red200,
            color = Red400,
            progress = rewardAnimatable.value
        )

        Text(
            text = "${(rewardAnimatable.value * 100).roundToInt()}%",
            style = MaterialTheme.typography.labelLarge,
            color = Color.White
        )
    }
}
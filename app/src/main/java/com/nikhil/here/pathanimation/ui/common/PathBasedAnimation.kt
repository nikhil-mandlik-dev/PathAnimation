package com.nikhil.here.pathanimation.ui.common

import android.graphics.PathMeasure
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex


@Composable
fun TranslatingObject(
    modifier: Modifier = Modifier,
    startDestination: Offset,
    endDestination: Offset,
    content: @Composable () -> Unit,
    onProgressUpdateListener: (progress: Float) -> Unit
) {
    val animationOffset = remember { mutableStateOf(Offset(0f, 0f)) }
    val shouldShow = remember { mutableStateOf(false) }
    val pathAnimatable = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        val pos = FloatArray(2)
        val tan = FloatArray(2)
        val pathToAnimate = Path().apply {
            val verticalDistance = endDestination.y - startDestination.y
            val horizontalDistance = endDestination.x - startDestination.x
            //cubic-bezier(.71,0,.29,1)
            val c1x = startDestination.x + (0.71 * horizontalDistance)
            val c1y = startDestination.y - (0.71 * verticalDistance)
            val c2x = startDestination.x + (0.29 * horizontalDistance)
            val c2y = endDestination.y
            moveTo(startDestination.x, startDestination.y)
            cubicTo(
                x1 = c1x.toFloat(),
                y1 = c1y.toFloat(),
                x2 = c2x.toFloat(),
                y2 = c2y.toFloat(),
                x3 = endDestination.x,
                y3 = endDestination.y
            )
        }
        PathMeasure().apply {
            setPath(pathToAnimate.asAndroidPath(), false)
            pathAnimatable.animateTo(1f, tween(1500)) {
                getPosTan(length * this.value, pos, tan)
                animationOffset.value = Offset(pos[0], pos[1])
                onProgressUpdateListener(this.value)
                when (this.value) {
                    0f -> {
                        shouldShow.value = true
                    }

                    1f -> {
                        shouldShow.value = false
                    }
                }
            }
        }
    }

    if (shouldShow.value) {
        Box(
            modifier = modifier
                .offset {
                    IntOffset(
                        (animationOffset.value.x).toInt(),
                        (animationOffset.value.y).toInt()
                    )
                }
                .zIndex(999f)
        ) {
            content()
        }
    }
}


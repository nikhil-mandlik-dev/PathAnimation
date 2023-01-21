package com.nikhil.here.pathanimation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikhil.here.pathanimation.ui.common.TranslatingObject
import com.nikhil.here.pathanimation.ui.theme.Red400
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

const val MAX_TRANSLATING_OBJECT_COUNT = 8

@Composable
fun MainScreen() {
    val scope = rememberCoroutineScope()

    var sourceOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }
    var destinationOffset by remember {
        mutableStateOf(Offset(0f, 0f))
    }

    var translatingObjectsCount by remember {
        mutableStateOf(0)
    }

    val translateAnimatable = remember {
        Animatable(initialValue = 0f)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        for (i in 1..translatingObjectsCount) {
            TranslatingObject(
                startDestination = sourceOffset,
                endDestination = destinationOffset,
                content = {
                    Box(
                        modifier = Modifier
                    ){
                        Text(text = "\uD83E\uDD2A", style = MaterialTheme.typography.headlineSmall)
                    }
                },
                onProgressUpdateListener = {

                }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            MyHorizontalList(translateAnimatable) { offset, size ->
                sourceOffset = Offset(
                    x = offset.x + (size.width / 2f) - 20f,
                    y = offset.y + (size.height / 2f) - 40f
                )
            }

            Spacer(modifier = Modifier.height(48.dp))


            MyHorizontalRow() { offset, size ->
                destinationOffset = Offset(
                    x = offset.x + (size.width / 2f) - 20f,
                    y = offset.y + (size.height / 2f) - 40f
                )
            }

            Spacer(modifier = Modifier.height(56.dp))

            Button(
                onClick = {
                    scope.launch {
                        translatingObjectsCount = 0
                        translateAnimatable.animateTo(0f)
                        translateAnimatable.animateTo(
                            targetValue = 1f,
                            animationSpec = tween(durationMillis = 3500)
                        ) {
                            translatingObjectsCount = (this.value * MAX_TRANSLATING_OBJECT_COUNT).roundToInt()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Red400,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Start Animation \uD83E\uDD2A")
            }
        }
    }
}


@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
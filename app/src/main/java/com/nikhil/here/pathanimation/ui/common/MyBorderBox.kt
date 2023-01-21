package com.nikhil.here.pathanimation.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nikhil.here.pathanimation.ui.theme.Grey200

@Composable
fun MyBorderBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 54.dp, minHeight = 54.dp)
            .border(
                border = BorderStroke(1.dp, color = Grey200), shape = RoundedCornerShape(12.dp)
            )
    )
}
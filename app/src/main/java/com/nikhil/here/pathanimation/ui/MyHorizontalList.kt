package com.nikhil.here.pathanimation.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.nikhil.here.pathanimation.ui.common.MyBorderBox


@Composable
fun MyHorizontalList(
    rewardAnimatable: Animatable<Float, AnimationVector1D>,
    updateSourceOffset: (Offset, IntSize) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(16, key = { item -> item }) {
            if (it == 2) {
                RewardItem(
                    rewardAnimatable = rewardAnimatable,
                    updateSourceOffset = updateSourceOffset
                )
            } else {
                MyBorderBox()
            }
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}


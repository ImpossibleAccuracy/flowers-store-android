package com.example.flowersstore.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.flowersstore.R

@Composable
fun Rating(
    modifier: Modifier = Modifier,
    rating: Float,
    maxRating: Int,
    size: Dp = 16.dp,
    spaceBetween: Dp = 0.dp,
    itemColor: Color = Color.Black
) {
    val imageEmpty = R.drawable.baseline_star_24
    val imageHalf = R.drawable.baseline_star_half_24
    val imageFilled = R.drawable.baseline_star_border_24

    val sizeModifier = Modifier.size(size)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(spaceBetween)
    ) {
        for (i in 1 until maxRating + 1) {
            if (i > rating && i - 1 < rating) {
                Image(
                    modifier = sizeModifier,
                    painter = painterResource(imageHalf),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(itemColor)
                )
            } else if (i > rating) {
                Image(
                    modifier = sizeModifier,
                    painter = painterResource(imageFilled),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(itemColor)
                )
            } else if (i <= rating) {
                Image(
                    modifier = sizeModifier,
                    painter = painterResource(imageEmpty),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(itemColor)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RatingPreview() {
    Rating(rating = 3f, maxRating = 5)
}

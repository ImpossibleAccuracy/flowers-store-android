package com.example.flowersstore.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ElevatedCard(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    elevation: Dp = 4.dp,
    cornerRadius: Dp = 12.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.elevatedCardElevation(elevation),
        content = content
    )
}

@Composable
fun TonalCard(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    cornerRadius: Dp = 12.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(
        modifier = modifier,
        color = color,
        elevation = 0.dp,
        cornerRadius = cornerRadius,
        content = content
    )
}

@Composable
fun OutlinedCard(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surface,
    outlineColor: Color = MaterialTheme.colorScheme.outline,
    outlineWidth: Dp = 1.dp,
    cornerRadius: Dp = 12.dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = color
        ),
        shape = RoundedCornerShape(cornerRadius),
        border = BorderStroke(outlineWidth, outlineColor),
        content = content
    )
}
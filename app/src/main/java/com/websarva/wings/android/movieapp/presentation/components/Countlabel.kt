package com.websarva.wings.android.movieapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CountLabel(
    imageVector: ImageVector,
    count: Int,
    iconTint: Color,
    modifier: Modifier = Modifier,
    color: Color  = LocalContentColor.current.copy(),
){
    Row (modifier = modifier){
        Icon(
            imageVector = imageVector,
            contentDescription = "人気度",
            tint = iconTint,
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = count.toString(),
            color = color,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 2.5.dp)
        )
    }
}
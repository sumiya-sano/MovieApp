package com.websarva.wings.android.movieapp.presentation.comment

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.websarva.wings.android.movieapp.domain.entity.Comment

@Composable
fun CommentsComponent(
    comment: Comment,
){
    Row (verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "・" + comment.commentBody ?: "No comment",
        )
        OutlinedIconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Edit,
                contentDescription = "edit",
                )
        }
        OutlinedIconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "delete",
            )
        }
    }
}
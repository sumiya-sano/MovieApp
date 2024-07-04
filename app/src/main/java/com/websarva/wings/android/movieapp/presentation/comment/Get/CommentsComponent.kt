package com.websarva.wings.android.movieapp.presentation.comment.Get

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import com.websarva.wings.android.movieapp.domain.entity.Comment

@Composable
fun CommentsComponent(
    comment: Comment,
    onEditComment: (Int,String) -> Unit,
    onDeleteComment: (Int) -> Unit
){
    var isEditing by remember { mutableStateOf(false) }
    var editedComment by remember { mutableStateOf(comment.commentBody ?: "") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row (verticalAlignment = Alignment.CenterVertically) {
        if (isEditing){
            TextField(
                value = editedComment,
                onValueChange = { editedComment = it },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onEditComment(comment.commentId ?: -1, editedComment)
                        isEditing = false
                        keyboardController?.hide()
                    }
                )
            )
        } else {
            Text(
                text = "・" + comment.commentBody ?: "No comment",
                modifier = Modifier.weight(1f)
            )
        }

        OutlinedIconButton(
            onClick = {
                isEditing = !isEditing
                if (!isEditing){
                    onEditComment(comment.commentId ?: -1, editedComment)
                    keyboardController?.hide()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "edit",
                )
        }


        OutlinedIconButton(
            onClick = {
                onDeleteComment(
                    // Todo comment.commentId ?: -1
                    // 999 //エラーの場合
                    222
                )
            }
        ) {
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "delete",
            )
        }
    }
}
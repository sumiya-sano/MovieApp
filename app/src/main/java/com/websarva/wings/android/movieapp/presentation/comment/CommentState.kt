package com.websarva.wings.android.movieapp.presentation.comment

import com.websarva.wings.android.movieapp.domain.entity.Comment
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse

data class CommentState(
    val isLoading: Boolean = false,
    val comments: List<Comment> = emptyList(),
    val error: String? = null,
)

package com.websarva.wings.android.movieapp.presentation.movie_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.websarva.wings.android.movieapp.domain.entity.Comment
import com.websarva.wings.android.movieapp.infrastructure.external_api.TmdbApiKeystore
import com.websarva.wings.android.movieapp.domain.entity.MovieDetail
import com.websarva.wings.android.movieapp.infrastructure.NetworkResponse
import com.websarva.wings.android.movieapp.presentation.comment.Get.CommentViewModel
import com.websarva.wings.android.movieapp.presentation.comment.Get.CommentsComponent
import com.websarva.wings.android.movieapp.presentation.comment.Post.PostCommentViewModel
import com.websarva.wings.android.movieapp.presentation.comment.Put.PutCommentViewModel
import com.websarva.wings.android.movieapp.presentation.comment.delete.DeleteCommentViewModel
import com.websarva.wings.android.movieapp.presentation.components.CountLabel

@Composable
fun MovieDetailScreen(
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
){
    val movieDetailState by movieDetailViewModel.state

        Box {
            when {
                movieDetailState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                !movieDetailState.error.isNullOrBlank() -> {
                    Text(
                        text = movieDetailState.error!!,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                else -> {
                    Column {
                        movieDetailState.movieDetail?.let { movieDetail ->
                            MovieDetailContent(movieDetail = movieDetail)
                        }
                    }
                }
            }
        }
}

@Composable
fun MovieDetailContent(
    movieDetail: MovieDetail,
    commentViewModel: CommentViewModel = hiltViewModel(),
    putCommentViewModel: PutCommentViewModel = hiltViewModel(),
    postCommentViewModel: PostCommentViewModel = hiltViewModel(),
    deleteCommentViewModel: DeleteCommentViewModel = hiltViewModel(),
    ) {
    val commentState by commentViewModel.state
    val putCommentState by putCommentViewModel.putCommentState.collectAsState()
    val postCommentState by postCommentViewModel.postCommentState.collectAsState()
    val deleteCommentState by deleteCommentViewModel.deleteCommentState.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    var postedComment by remember { mutableStateOf("") }

    LaunchedEffect(movieDetail.movieId) {
        commentViewModel.getComments(
            3258
        //    movieDetail.movieId
        )
    }


    Column (modifier = Modifier.verticalScroll(rememberScrollState())) {
        Box (modifier = Modifier.heightIn(min = 200.dp)) {
            var isLoadingImage by remember { mutableStateOf(true) }
            if (isLoadingImage) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            AsyncImage(
                model = TmdbApiKeystore.IMAGE_URL +movieDetail.posterPath,
                contentDescription = movieDetail.overview,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomStartPercent = 5,
                            bottomEndPercent = 5,
                        )
                    ),
                onSuccess = { isLoadingImage = false },
            )
        }

        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = movieDetail.title ?: "No title",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = movieDetail.overview ?: "No overview",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(20.dp))
            CountLabel(
                imageVector = Icons.Default.Star,
                count = movieDetail.popularity?.toInt() ?: 0,
                iconTint = Color.Magenta,
            )
            Spacer(modifier = Modifier.height(20.dp))
            val genreNames = movieDetail.genres?.map { it?.name }
            val genres = genreNames?.joinToString(", ")
            Text(text = "ジャンル: ${genres}")
            val productionCompaniesNames = movieDetail.productionCompanies?.map { it?.name }
            val productionCompanies = productionCompaniesNames?.joinToString(", ")
            Text(text = "制作会社: ${productionCompanies}")
            if (commentState.comments.isNotEmpty()) {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
            Column {
                Row (verticalAlignment = Alignment.CenterVertically){
                    TextField(
                        value = postedComment,
                        onValueChange = {postedComment = it},
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                postCommentViewModel.postComment(
                                    userId = 111, //Todo
                                    movieId = 3258,//Todo movieDetail.movieId ?: -1
                                    commentBody = postedComment
                                )
                                keyboardController?.hide()
                            }
                        )
                    )
                }
                CommentsList(
                    comments = commentState.comments,
                    onEditComment = { commentId, editedComment ->
                        putCommentViewModel.putComment(commentId = commentId, commentBody = editedComment)
                    },
                    onDeleteComment = {commentId ->
                        deleteCommentViewModel.deleteComment(commentId)
                    }
                )
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))


            when (putCommentState) { //Todo 別コンポーネントにしてダイアログかトーストにする
                is NetworkResponse.Loading -> {
                }
                is NetworkResponse.Success -> {
                    Text(
                        text = "Comment updated successfully!",
                        color = Color.Green,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                is NetworkResponse.Failure -> {
                    (putCommentState as NetworkResponse.Failure).error?.let {
                        Text(
                            text = it,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }

            when (postCommentState) { //Todo 別コンポーネントにしてダイアログかトーストにする
                is NetworkResponse.Loading -> {
                }
                is NetworkResponse.Success -> {
                    Text(
                        text = "Comment post successfully!",
                        color = Color.Green,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                is NetworkResponse.Failure -> {
                    (postCommentState as NetworkResponse.Failure).error?.let {
                        Text(
                            text = it,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }

            when (deleteCommentState) { //Todo 別コンポーネントにしてダイアログかトーストにする
                is NetworkResponse.Loading -> {
                }
                is NetworkResponse.Success -> {
                    Text(
                        text = "Comment delete successfully!",
                        color = Color.Green,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                is NetworkResponse.Failure -> {
                    (deleteCommentState as NetworkResponse.Failure).error?.let {
                        Text(
                            text = it,
                            color = Color.Red,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
    }
}

@Composable
fun CommentsList(
    comments: List<Comment>,
    onEditComment: (Int, String) -> Unit,
    onDeleteComment: (Int) -> Unit,
    ) {
    Column(modifier = Modifier.padding(5.dp)) {
        comments.forEach { comment ->
            CommentsComponent(
                comment = comment,
                onEditComment = onEditComment,
                onDeleteComment = onDeleteComment,
            )
            Spacer(modifier = Modifier.height(8.dp)) // 各コメントの間にスペースを追加
        }
    }
}
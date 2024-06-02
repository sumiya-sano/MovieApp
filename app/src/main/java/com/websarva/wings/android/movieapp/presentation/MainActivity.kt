package com.websarva.wings.android.movieapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.websarva.wings.android.movieapp.presentation.keyword_search_movie.KeywordSearchMoviesScreen
import com.websarva.wings.android.movieapp.presentation.movie_detail.MovieDetailScreen
import com.websarva.wings.android.movieapp.presentation.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        //映画検索画面をデフォルト画面に設定
                        startDestination =ScreenRoute.SearchMoviesScreen.route,
                    ) {
                        //映画検索画面　routeと画面を紐付け
                        composable(route = ScreenRoute.SearchMoviesScreen.route){
                            KeywordSearchMoviesScreen(navController)
                        }
                        //映画詳細画面　routeと画面を紐付け
                        composable(route = ScreenRoute.MovieDetailScreen.route+ "/{movieId}"){
                            MovieDetailScreen()
                        }
                    }
                }
            }
        }
    }
}


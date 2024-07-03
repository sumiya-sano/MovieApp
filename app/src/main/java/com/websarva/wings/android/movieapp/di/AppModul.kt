package com.websarva.wings.android.movieapp.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.websarva.wings.android.movieapp.domain.repository_interface.comment.CommentRepository
import com.websarva.wings.android.movieapp.infrastructure.external_api.TmdbApiKeystore.BASE_URL
import com.websarva.wings.android.movieapp.infrastructure.external_api.ThemoviedbAPI
import com.websarva.wings.android.movieapp.infrastructure.repository_implement.MovieRepositoryImpl
import com.websarva.wings.android.movieapp.domain.repository_interface.movie.MovieRepository
import com.websarva.wings.android.movieapp.infrastructure.internal_api.CommentsAPI
import com.websarva.wings.android.movieapp.infrastructure.repository_implement.CommentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModul {
    @Provides
    @Singleton
    fun provideThemoviedbApi(): ThemoviedbAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(ThemoviedbAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: ThemoviedbAPI): MovieRepository {
        return MovieRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Provides
    @Singleton
    fun provideCommentsAPI(gson: Gson): CommentsAPI {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CommentsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentRepository(api: CommentsAPI): CommentRepository {
        return CommentRepositoryImpl(api)
    }
}
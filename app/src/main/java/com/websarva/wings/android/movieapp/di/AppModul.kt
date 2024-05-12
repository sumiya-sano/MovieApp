package com.websarva.wings.android.movieapp.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.websarva.wings.android.movieapp.common.Constants.BASE_URL
import com.websarva.wings.android.movieapp.data.remote.ThemoviedbAPI
import com.websarva.wings.android.movieapp.data.repository.MovieRepositoryImpl
import com.websarva.wings.android.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
    fun provideMovieRepository(api: ThemoviedbAPI): MovieRepository{
        return MovieRepositoryImpl(api)
    }
}
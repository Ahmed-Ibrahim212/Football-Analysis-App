package com.example.footballdataapp.di

import com.example.footballdataapp.data.local.TeamsDatabase
import com.example.footballdataapp.network.NetworkConstants.Companion.BASE_URL
import com.example.footballdataapp.network.FootballApi
import com.example.footballdataapp.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun provideClient(logger: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideClientAPI(retrofit: Retrofit): FootballApi =
        retrofit.create(FootballApi::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(api: FootballApi,teamsDatabase: TeamsDatabase): AuthRepository {
       return AuthRepository(api, teamsDatabase)
    }
}
package com.mozzartinterview.core.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.opap.gr/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(OkHttpClient.Builder().build())
        .build()
}

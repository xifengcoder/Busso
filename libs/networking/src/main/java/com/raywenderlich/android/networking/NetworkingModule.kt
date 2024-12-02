package com.raywenderlich.android.networking

import android.app.Application
import com.google.gson.GsonBuilder
import com.raywenderlich.android.di.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkingModule {
    @Provides
    @ApplicationScope
    fun provideCache(networkConfiguration: NetworkConfiguration, application: Application): Cache =
        Cache(
            application.cacheDir,
            networkConfiguration.cacheSize // 1
        )

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(cache: Cache): OkHttpClient =
        OkHttpClient.Builder().cache(cache).build()

    @Provides
    @ApplicationScope
    fun provideRetrofit(
        networkConfiguration: NetworkConfiguration,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(networkConfiguration.serverBaseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setDateFormat(networkConfiguration.dateFormat)
                        .create()
                )
            )
            .client(okHttpClient)
            .build()
}
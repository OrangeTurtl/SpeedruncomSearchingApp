package com.stepanf.speedruncomapiapp.di

import com.stepanf.speedruncomapiapp.api.SpeedrunDetailsApi
import com.stepanf.speedruncomapiapp.api.SpeedrunGamesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(SpeedrunDetailsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideSpeedrunGamesApi(retrofit: Retrofit): SpeedrunGamesApi =
        retrofit.create(SpeedrunGamesApi::class.java)

    @Provides
    @Singleton
    fun provideSpeedrunDetailsApi(retrofit: Retrofit): SpeedrunDetailsApi =
        retrofit.create(SpeedrunDetailsApi::class.java)
}
package com.example.tejuprojectdiagnal.di

import android.content.Context
import com.example.tejuprojectdiagnal.mvvm.helper_class.JsonHelperClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationLevelDi {

    @Singleton
    @Provides
    fun getJsonHelper(@ApplicationContext context: Context): JsonHelperClass = JsonHelperClass(context)

}
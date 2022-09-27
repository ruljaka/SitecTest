package com.ruslangrigoriev.sitectest.di

import android.content.Context
import com.ruslangrigoriev.sitectest.data.ApiService
import com.ruslangrigoriev.sitectest.data.RepositoryImpl
import com.ruslangrigoriev.sitectest.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApiService,
        @ApplicationContext appContext: Context
    ): Repository {
        return RepositoryImpl(apiService, appContext)
    }
}
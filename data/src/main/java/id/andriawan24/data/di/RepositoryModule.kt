package id.andriawan24.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.andriawan24.data.repository.NewsRepository
import id.andriawan24.domain.repository.INewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesNewsRepository(newsRepository: NewsRepository): INewsRepository
}
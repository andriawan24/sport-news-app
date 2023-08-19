package id.andriawan24.domain.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.andriawan24.domain.usecases.GetFavoritesNewsUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    fun getFavoritesUseCase(): GetFavoritesNewsUseCase
}
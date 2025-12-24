package com.eunbi.character.di

import com.eunbi.character.repository.CharacterRepository
import com.eunbi.character.repository.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharacterRepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(): CharacterRepository {
        return CharacterRepositoryImpl()
    }
}
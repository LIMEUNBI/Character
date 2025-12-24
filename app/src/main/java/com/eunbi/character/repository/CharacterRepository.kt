package com.eunbi.character.repository

import com.eunbi.character.model.CharacterInfo

interface CharacterRepository {

    suspend fun getCharacterList(): CharacterInfo

    suspend fun getCharacterSearch(name: String): CharacterInfo
}
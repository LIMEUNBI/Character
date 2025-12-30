package com.eunbi.character.repository

import com.eunbi.character.model.CharacterInfo

interface CharacterRepository {

    suspend fun getCharacterList(page: Int): CharacterInfo

    suspend fun getCharacterSearch(name: String): CharacterInfo
}
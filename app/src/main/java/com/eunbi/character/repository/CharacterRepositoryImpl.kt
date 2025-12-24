package com.eunbi.character.repository

import com.eunbi.character.model.CharacterInfo
import com.eunbi.character.network.ApiServiceBuilder
import com.eunbi.character.network.CharacterApiInterface
import com.eunbi.character.network.apiCallAndReturnThrow

class CharacterRepositoryImpl: CharacterRepository {
    override suspend fun getCharacterList(): CharacterInfo {
        return apiCallAndReturnThrow {
            ApiServiceBuilder().apiGeneralBuilder(CharacterApiInterface::class.java)
                .getCharacterList()
        }
    }

    override suspend fun getCharacterSearch(name: String): CharacterInfo {
        return apiCallAndReturnThrow {
            ApiServiceBuilder().apiGeneralBuilder(CharacterApiInterface::class.java)
                .getCharacterSearch(name)
        }
    }
}
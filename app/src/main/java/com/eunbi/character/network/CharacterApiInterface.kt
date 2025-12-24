package com.eunbi.character.network

import com.eunbi.character.model.CharacterInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiInterface {

    @GET("character")
    suspend fun getCharacterList(): CharacterInfo

    @GET("character")
    suspend fun getCharacterSearch(
        @Query("name") name: String
    ): CharacterInfo
}

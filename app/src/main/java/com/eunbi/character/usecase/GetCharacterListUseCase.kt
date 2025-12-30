package com.eunbi.character.usecase

import com.eunbi.character.model.CharacterInfo
import com.eunbi.character.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend fun execute(page: Int): CharacterInfo = repository.getCharacterList(page)
}
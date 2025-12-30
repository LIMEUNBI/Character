package com.eunbi.character.ui.list

import com.eunbi.character.base.ViewEffect
import com.eunbi.character.base.ViewEvent
import com.eunbi.character.base.ViewState
import com.eunbi.character.model.Character
import com.eunbi.character.model.CharacterInfo

class ListContract {

    sealed class Event: ViewEvent {
        data class ClickToDetail(val character: Character) : Event()
        data object ClickToSearch : Event()
    }

    data class State(
        val characterList: CharacterInfo = CharacterInfo(),
        val page: Int = 1
    ): ViewState

    sealed class Effect: ViewEffect {
        data class MoveToDetail(val character: Character) : Effect()
        data object MoveToSearch: Effect()
    }
}
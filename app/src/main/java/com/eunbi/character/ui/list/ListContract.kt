package com.eunbi.character.ui.list

import com.eunbi.character.base.ViewEffect
import com.eunbi.character.base.ViewEvent
import com.eunbi.character.base.ViewState
import com.eunbi.character.model.CharacterInfo

class ListContract {

    sealed class Event: ViewEvent {
        data class ClickToDetail(val characterInfo: CharacterInfo) : Event()
    }

    data class State(
        val characterList: CharacterInfo
    ): ViewState

    sealed class Effect: ViewEffect {
        data class MoveToDetail(val characterInfo: CharacterInfo) : Effect()
    }
}
package com.eunbi.character.ui.search

import com.eunbi.character.base.ViewEffect
import com.eunbi.character.base.ViewEvent
import com.eunbi.character.base.ViewState
import com.eunbi.character.model.CharacterInfo

class SearchContract {
    sealed class Event: ViewEvent {
        data object ClickToBack : Event()
    }

    data class State(
        val searchKeyword: String = "",
        val characterInfo: CharacterInfo
    ): ViewState

    sealed class Effect: ViewEffect {
        data object MoveToBack : Effect()

    }
}
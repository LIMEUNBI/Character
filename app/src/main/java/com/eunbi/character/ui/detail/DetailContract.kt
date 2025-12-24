package com.eunbi.character.ui.detail

import com.eunbi.character.base.ViewEffect
import com.eunbi.character.base.ViewEvent
import com.eunbi.character.base.ViewState
import com.eunbi.character.model.CharacterInfo

class DetailContract {

    sealed class Event: ViewEvent {
        data object ClickToBack: Event()
    }

    data class State(
        val characterInfo: CharacterInfo
    ): ViewState

    sealed class Effect: ViewEffect {
        data object MoveToBack: Effect()
    }
}
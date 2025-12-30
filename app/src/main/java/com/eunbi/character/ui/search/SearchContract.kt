package com.eunbi.character.ui.search

import com.eunbi.character.base.ViewEffect
import com.eunbi.character.base.ViewEvent
import com.eunbi.character.base.ViewState
import com.eunbi.character.model.Character
import com.eunbi.character.model.CharacterInfo
import com.eunbi.character.ui.list.ListContract.Event

class SearchContract {
    sealed class Event: ViewEvent {
        data object ClickToBack : Event()
        data class ClickToDetail(val character: Character) : Event()
        data class ChangeSearchKeyword(val keyword: String): Event()
    }

    data class State(
        val searchKeyword: String = "",
        val characterInfo: CharacterInfo = CharacterInfo()
    ): ViewState

    sealed class Effect: ViewEffect {
        data object MoveToBack : Effect()
        data class MoveToDetail(val character: Character) : Effect()

    }
}
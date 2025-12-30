package com.eunbi.character.ui.detail

import com.eunbi.character.base.BaseViewModel

class DetailViewModel : BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {

    override fun setInitialState() = DetailContract.State()

    override fun handleEvents(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.ClickToBack -> {
                setEffect { DetailContract.Effect.MoveToBack }
            }

            is DetailContract.Event.SetInfo -> {
                setState { copy(character = event.character) }
            }
        }
    }
}
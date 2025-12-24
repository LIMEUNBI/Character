package com.eunbi.character.ui.detail

import com.eunbi.character.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DetailViewModel: BaseViewModel<DetailContract.Event, DetailContract.State, DetailContract.Effect>() {

    override fun setInitialState(): DetailContract.State {
        TODO("Not yet implemented")
    }

    override fun handleEvents(event: DetailContract.Event) {
        TODO("Not yet implemented")
    }

}
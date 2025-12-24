package com.eunbi.character.ui.list

import com.eunbi.character.base.BaseViewModel
import com.eunbi.character.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : BaseViewModel<ListContract.Event, ListContract.State, ListContract.Effect>() {

    override fun setInitialState(): ListContract.State {
        TODO("Not yet implemented")
    }

    override fun handleEvents(event: ListContract.Event) {
        TODO("Not yet implemented")
    }

}
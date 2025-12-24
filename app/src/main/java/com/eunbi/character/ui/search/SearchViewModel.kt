package com.eunbi.character.ui.search

import com.eunbi.character.base.BaseViewModel
import com.eunbi.character.usecase.GetCharacterSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharacterUseCase: GetCharacterSearchUseCase
): BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {

    override fun setInitialState(): SearchContract.State {
        TODO("Not yet implemented")
    }

    override fun handleEvents(event: SearchContract.Event) {
        TODO("Not yet implemented")
    }
}
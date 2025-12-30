package com.eunbi.character.ui.search

import androidx.lifecycle.viewModelScope
import com.eunbi.character.base.BaseViewModel
import com.eunbi.character.usecase.GetCharacterSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCharacterUseCase: GetCharacterSearchUseCase
): BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {

    override fun setInitialState() = SearchContract.State()

    override fun handleEvents(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.ClickToBack -> {
                setEffect { SearchContract.Effect.MoveToBack }
            }

            is SearchContract.Event.ClickToDetail -> {
                setEffect { SearchContract.Effect.MoveToDetail(event.character) }
            }

            is SearchContract.Event.ChangeSearchKeyword -> {
                setState { copy(searchKeyword = event.keyword) }
                searchCharacter()
            }
        }
    }

    fun searchCharacter() {
        viewModelScope.launch {
            try {
                val searchList = searchCharacterUseCase.execute(viewState.value.searchKeyword)
                setState { copy(characterInfo = searchList) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
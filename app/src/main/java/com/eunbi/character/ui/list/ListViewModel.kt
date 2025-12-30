package com.eunbi.character.ui.list

import androidx.lifecycle.viewModelScope
import com.eunbi.character.base.BaseViewModel
import com.eunbi.character.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : BaseViewModel<ListContract.Event, ListContract.State, ListContract.Effect>() {

    init {
        viewModelScope.launch {
            try {
                val getList = getCharacterListUseCase.execute()
                setState { copy(characterList = getList) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun setInitialState() = ListContract.State()

    override fun handleEvents(event: ListContract.Event) {
        when (event) {
            is ListContract.Event.ClickToDetail -> {
                setEffect { ListContract.Effect.MoveToDetail(event.character) }
            }

            is ListContract.Event.ClickToSearch -> {
                setEffect { ListContract.Effect.MoveToSearch }
            }
        }
    }
}
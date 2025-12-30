package com.eunbi.character.ui.list

import androidx.lifecycle.viewModelScope
import com.eunbi.character.base.BaseViewModel
import com.eunbi.character.model.CharacterInfo
import com.eunbi.character.usecase.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase
) : BaseViewModel<ListContract.Event, ListContract.State, ListContract.Effect>() {

    init {
        getCharacterList()
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

    fun getCharacterList() {
        viewModelScope.launch {
            if (viewState.value.characterList.info.next != null) {
                try {
                    val getList = getCharacterListUseCase.execute(viewState.value.page)
                    if (viewState.value.characterList.results.isEmpty()) {
                        setState { copy(characterList = getList) }
                    }

                    val list = viewState.value.characterList
                    list.results.addAll(getList.results)
                    setState { copy(characterList = list) }
                    setState { copy(page = viewState.value.page + 1) }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
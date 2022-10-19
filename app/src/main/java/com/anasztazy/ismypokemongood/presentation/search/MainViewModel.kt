package com.anasztazy.ismypokemongood.presentation.search

import androidx.lifecycle.*
import com.anasztazy.ismypokemongood.data.search.model.PokemonDataModel
import com.anasztazy.ismypokemongood.domain.search.usecase.GetListPokemonUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch

class MainViewModel(

    val getListPokemonUseCase: GetListPokemonUseCase

) : ViewModel() {

    val pokemonLiveData: MutableLiveData<Map<String, List<PokemonDataModel>>> by lazy {
        MutableLiveData<Map<String, List<PokemonDataModel>>>()
    }

    private val _events = Channel<Event>(capacity = Channel.UNLIMITED)
    val events: ReceiveChannel<Event> = _events

    fun buttonSearchClicked(text: String) {

        viewModelScope.launch {

            var typedTextToSearch: String = text
            var pokeList = getListPokemonUseCase.invoke()
            var pokemonFound: MutableMap<String, List<PokemonDataModel>> = mutableMapOf()

            pokeList.forEach() {

                var found = it.value.filter { poke ->
                    poke.pokemonName == typedTextToSearch
                }

                if (found.isNotEmpty()){

                    pokemonFound.put(it.key, found)

                }
            }

            pokemonLiveData.value = pokemonFound
            _events.trySend(Event.NavigateToPokemonFragment(pokemonFound))
        }
    }


    sealed class Event(){
        //object NavigateToPokemonFragment: Event() обычный ивент без параметров
        data class NavigateToPokemonFragment(val arg: Map<String,List<PokemonDataModel>>): Event()
    }


}







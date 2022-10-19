package com.anasztazy.ismypokemongood.domain.search.adapter

import com.anasztazy.ismypokemongood.data.search.model.PokemonDataModel

interface PokemonRepository {
    fun getPokemons(): Map<String, List<PokemonDataModel>>
}
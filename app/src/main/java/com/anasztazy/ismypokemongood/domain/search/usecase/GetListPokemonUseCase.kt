package com.anasztazy.ismypokemongood.domain.search.usecase

import com.anasztazy.ismypokemongood.data.search.model.PokemonDataModel
import com.anasztazy.ismypokemongood.domain.search.adapter.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class GetListPokemonUseCase(
    val pokemonRepository: PokemonRepository
) {

    suspend fun invoke(): Map<String, List<PokemonDataModel>> =
        withContext(Dispatchers.IO){
            return@withContext pokemonRepository.getPokemons()
        }
}
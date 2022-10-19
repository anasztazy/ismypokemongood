package com.anasztazy.ismypokemongood.presentation

import com.anasztazy.ismypokemongood.domain.search.adapter.PokemonRepository

interface DependencyProvider {
    fun providePokeRepository(): PokemonRepository
}
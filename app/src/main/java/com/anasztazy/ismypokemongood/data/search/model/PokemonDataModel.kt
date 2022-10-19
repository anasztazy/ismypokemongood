package com.anasztazy.ismypokemongood.data.search.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDataModel(
    var pokemonName: String,
    var fastMove: String,
    var chargedMove: String,
    var damagePerSecond: Double,
    var totalDamageOutput: Double,
    var raidPerformance: Double,
    var combatPower: Int,
    var pokemonType: String
)




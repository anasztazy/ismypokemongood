package com.anasztazy.ismypokemongood.data.search

import com.anasztazy.ismypokemongood.data.search.model.PokemonDataModel

object Utils {

    fun stringToPokemon(str: String, pokemonType: String): PokemonDataModel {
        var x = str.replace("\"", "").split(",")

        var pokemon = PokemonDataModel(
            pokemonName = x[0],
            fastMove = x[1],
            chargedMove = x[2],
            damagePerSecond = x[3].toDouble(),
            totalDamageOutput = x[4].toDouble(),
            raidPerformance = x[5].toDouble(),
            combatPower = x[6].toInt(),
            pokemonType = pokemonType

        )

        return pokemon
    }

    fun getPokemonType(str: String): String {
        return str.dropLast(4)
    }

}
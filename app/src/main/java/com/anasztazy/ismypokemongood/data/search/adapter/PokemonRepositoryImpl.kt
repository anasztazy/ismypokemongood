package com.anasztazy.ismypokemongood.data.search.adapter

import com.anasztazy.ismypokemongood.Config
import com.anasztazy.ismypokemongood.data.search.Utils
import com.anasztazy.ismypokemongood.data.search.model.PokemonDataModel
import com.anasztazy.ismypokemongood.data.search.rest.CSVReader
import com.anasztazy.ismypokemongood.domain.search.adapter.PokemonRepository


class PokemonRepositoryImpl(
    val csvReader: CSVReader
) : PokemonRepository {


    override fun getPokemons(): Map<String, List<PokemonDataModel>> {


        val mapPokemons: MutableMap<String, List<PokemonDataModel>> = mutableMapOf()

        Config.PokeFiles.values().forEach { pokeFile ->

            val listPokemonsByType: MutableList<PokemonDataModel> = mutableListOf()

            val listStringPokemon = csvReader.read(pokeFile.fileName)

            listStringPokemon.forEach { stringPokemon ->

                val pokemon =
                    Utils.stringToPokemon(stringPokemon, Utils.getPokemonType(pokeFile.fileName))

                listPokemonsByType.add(pokemon)
            }

            mapPokemons.put(Utils.getPokemonType(pokeFile.fileName), listPokemonsByType)

        }

        return mapPokemons
    }

}
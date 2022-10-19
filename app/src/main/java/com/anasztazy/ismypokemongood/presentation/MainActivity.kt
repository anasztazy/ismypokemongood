package com.anasztazy.ismypokemongood.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anasztazy.ismypokemongood.R
import com.anasztazy.ismypokemongood.data.search.adapter.PokemonRepositoryImpl
import com.anasztazy.ismypokemongood.data.search.model.PokemonDataModel
import com.anasztazy.ismypokemongood.data.search.rest.CSVReader
import com.anasztazy.ismypokemongood.domain.search.adapter.PokemonRepository

class MainActivity : AppCompatActivity(), DependencyProvider {

    private lateinit var csvReader: CSVReader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        csvReader = CSVReader(applicationContext)

    }

    override fun providePokeRepository(): PokemonRepository {
        val pokeRepository: PokemonRepository = PokemonRepositoryImpl(csvReader)
        return pokeRepository
    }

}

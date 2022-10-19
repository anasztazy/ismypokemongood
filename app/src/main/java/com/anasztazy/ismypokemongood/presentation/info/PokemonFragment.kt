package com.anasztazy.ismypokemongood.presentation.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.anasztazy.ismypokemongood.data.search.model.PokemonDataModel
import com.anasztazy.ismypokemongood.databinding.FragmentPokemonBinding
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class PokemonFragment : Fragment() {

    private lateinit var binding: FragmentPokemonBinding

    private lateinit var viewModel: PokemonViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        binding = FragmentPokemonBinding.inflate(layoutInflater)
        val view = binding.root


        val args: PokemonFragmentArgs by navArgs()
        val amount = args.argSearch
        val x = Json.decodeFromString<Map<String, List<PokemonDataModel>>>(amount)
        binding.tvPokemonName.text = "$x"



        binding.btnBack.setOnClickListener() {
            var navController = findNavController()
            navController.popBackStack()
        }

        return view
    }


}
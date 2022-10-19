package com.anasztazy.ismypokemongood.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.anasztazy.ismypokemongood.R
import com.anasztazy.ismypokemongood.databinding.FragmentMainBinding
import com.anasztazy.ismypokemongood.domain.search.usecase.GetListPokemonUseCase
import com.anasztazy.ismypokemongood.presentation.DependencyProvider
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val mainViewModel: MainViewModel by activityViewModels<MainViewModel> {
        object : AbstractSavedStateViewModelFactory(this, null) {
            override fun <T : ViewModel?> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle
            ): T {
                return MainViewModel(
                    GetListPokemonUseCase((requireActivity() as DependencyProvider).providePokeRepository())
                ) as T
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(layoutInflater)
        val view = binding.root

        viewLifecycleOwner.lifecycleScope.launch() {

            mainViewModel.events.receiveAsFlow().collect() { event ->

                when (event){
                    is MainViewModel.Event.NavigateToPokemonFragment -> {

                        val json = Json.encodeToString(event.arg)

                        var bundle = Bundle()
                        bundle.putString("argSearch", json)

                        var navController = findNavController()
                        navController.navigate(R.id.action_mainFragment_to_pokemonFragment, bundle)

                    }
                }
            }
        }



        binding.btnSearch.setOnClickListener() {

            val typedText: String = binding.etTypeToSearch.text.toString()
            mainViewModel.buttonSearchClicked(typedText)

        }

        return view
    }
}



package com.example.renshu.practice.alphabet

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentAlphabetBinding
import com.example.renshu.practice.adapter.SinglePracticeItemAdapter
import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shupresentation.generic.mvvm.UIState
import com.example.shupresentation.practice.alphabet.AlphabetNavigationAction
import com.example.shupresentation.practice.alphabet.AlphabetViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AlphabetFragment : DaggerFragment(R.layout.fragment_alphabet) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<AlphabetViewModel> { viewModelFactory }
    private val ui by viewBinding<FragmentAlphabetBinding>()

    private val args by navArgs<AlphabetFragmentArgs>()
    private var characterItemAdapter = SinglePracticeItemAdapter { character -> viewModel.openOtherCharacter(character) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ui.character.text = args.character

        observeViewModel()
        initToolbar()
    }

    private fun observeViewModel() {
        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigationAction)
        viewModel.uiState.observe(viewLifecycleOwner, ::handleUIState)
        viewModel.alphabet.observe(viewLifecycleOwner, ::initRecyclerView)
    }

    private fun handleNavigationAction(action: AlphabetNavigationAction) {
        when (action) {
            is AlphabetNavigationAction.OpenOtherCharacter -> {
                val scrollPosition = (ui.otherHiraganaRecyclerView.layoutManager as LinearLayoutManager)
                    .findFirstCompletelyVisibleItemPosition()

                findNavController().navigate(
                    AlphabetFragmentDirections.actionFragmentAlphabetSelf()
                        .setAlphabet(args.alphabet)
                        .setCharacter(action.character)
                        .setPosition(scrollPosition.toLong())
                )
            }
        }
    }

    private fun handleUIState(uiState: UIState) {
        when (uiState) {
            UIState.NORMAL -> {}
            UIState.LOADING -> {}
            UIState.ERROR -> {}
        }
    }

    private fun initToolbar() {
        ui.collapsingToolbar.title = getString(R.string.alphabet_title, args.alphabet, args.character)
        ui.toolbar.setNavigationOnClickListener {
            findNavController().navigate(AlphabetFragmentDirections.actionAlphabetFragmentToPracticeFragment())
        }
    }

    private fun initRecyclerView(characters: List<AlphabetCharacter>) {
        ui.otherHiraganaRecyclerView.apply {
            adapter = characterItemAdapter
            LinearSnapHelper().attachToRecyclerView(this)
        }

        if (args.position.toInt() != -1) {
            (ui.otherHiraganaRecyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(args.position.toInt(), 155)
        }
        characterItemAdapter.apply {
            setSmallItems(true)
            submitList(characters)
        }
    }
}

package com.example.renshu.practice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentPracticeBinding
import com.example.renshu.practice.adapter.CustomListAdapter
import com.example.renshu.practice.adapter.SinglePracticeItemAdapter
import com.example.shudomain.practice.model.AlphabetCharacter
import com.example.shupresentation.generic.mvvm.UIState
import com.example.shupresentation.practice.PracticeNavigationAction
import com.example.shupresentation.practice.PracticeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PracticeFragment : DaggerFragment(R.layout.fragment_practice) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PracticeViewModel> { viewModelFactory }
    private val ui by viewBinding<FragmentPracticeBinding>()

    private var hiraganaItemAdapter = SinglePracticeItemAdapter { character -> viewModel.openHiragana(character) }
    private var katakanaItemAdapter = SinglePracticeItemAdapter { character -> viewModel.openKatakana(character) }
    //private var customListAdapter = CustomListAdapter(viewModel::openWordList) { viewModel.deleteList(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        initButtons()
    }

    private fun observeViewModel() {
        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigationAction)
        viewModel.uiState.observe(viewLifecycleOwner, ::handleUIState)
        viewModel.hiragana.observe(viewLifecycleOwner, ::hiraganaRecyclerView)
        viewModel.katakana.observe(viewLifecycleOwner, ::katakanaRecyclerView)
    }

    private fun handleNavigationAction(action: PracticeNavigationAction) {
        when (action) {
            is PracticeNavigationAction.OpenHiragana -> openHiraganaCharacter(action.character)
            is PracticeNavigationAction.OpenKatakana -> openKatakanaCharacter(action.character)
            is PracticeNavigationAction.OpenList -> {}
            is PracticeNavigationAction.OpenHiraganaPractice -> openKanaExercise(action.alphabet)
            is PracticeNavigationAction.OpenKatakanaPractice -> openKanaExercise(action.alphabet)
            PracticeNavigationAction.OpenAddList -> openAddList()
        }
    }

    private fun openHiraganaCharacter(character: String) {
        findNavController().navigate(
            PracticeFragmentDirections
                .actionPracticeFragmentToAlphabetFragment()
                .setCharacter(character)
                .setAlphabet(getString(R.string.alphabet_hiragana))
        )
    }

    private fun openKanaExercise(alphabet: String) {
        findNavController().navigate(
            PracticeFragmentDirections
                .actionFragmentPracticeToFragmentKanaExercise()
                .setAlphabet(alphabet)
        )
    }

    private fun openKatakanaCharacter(character: String) {
        findNavController().navigate(
            PracticeFragmentDirections
                .actionPracticeFragmentToAlphabetFragment()
                .setCharacter(character)
                .setAlphabet(getString(R.string.alphabet_katakana))
        )
    }

    private fun openAddList() {
        findNavController().navigate(
            PracticeFragmentDirections.actionFragmentPracticeToFragmentListAdd()
        )
    }

    private fun handleUIState(state: UIState) {
        when (state) {
            UIState.NORMAL -> {
                enablePractice()
            }
            UIState.LOADING -> {
                disablePractice()
            }
            UIState.ERROR -> {
                disablePractice()
            }
        }
    }

    private fun hiraganaRecyclerView(characters: List<AlphabetCharacter>) {
        ui.hiraganaRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = hiraganaItemAdapter
            LinearSnapHelper().attachToRecyclerView(this)
        }

        hiraganaItemAdapter.submitList(characters)
    }

    private fun katakanaRecyclerView(characters: List<AlphabetCharacter>) {
        ui.katakanaRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = katakanaItemAdapter
            LinearSnapHelper().attachToRecyclerView(this)
        }

        katakanaItemAdapter.submitList(characters)
    }

    private fun initButtons() {
        ui.hiraganaPracticeButton.setOnClickListener {
            viewModel.openHiraganaPractice(requireContext().getString(R.string.alphabet_hiragana).lowercase())
        }

        ui.katakanaPracticeButton.setOnClickListener {
            viewModel.openKatakanaPractice(requireContext().getString(R.string.alphabet_katakana).lowercase())
        }

        ui.listAddButton.setOnClickListener {
            viewModel.openAddList()
        }
    }

    private fun enablePractice() {
        ui.hiraganaPracticeButton.isEnabled = true
        ui.katakanaPracticeButton.isEnabled = true

        ui.katakanaRecyclerView.visibility = View.VISIBLE
        ui.hiraganaRecyclerView.visibility = View.VISIBLE

        ui.katakanaLoadingBar.visibility = View.GONE
        ui.hiraganaLoadingBar.visibility = View.GONE
    }

    private fun disablePractice() {
        ui.hiraganaPracticeButton.isEnabled = false
        ui.katakanaPracticeButton.isEnabled = false

        ui.katakanaRecyclerView.visibility = View.GONE
        ui.hiraganaRecyclerView.visibility = View.GONE

        ui.katakanaLoadingBar.visibility = View.VISIBLE
        ui.hiraganaLoadingBar.visibility = View.VISIBLE
    }
}

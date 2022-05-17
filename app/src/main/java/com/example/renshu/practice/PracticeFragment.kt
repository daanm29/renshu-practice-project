package com.example.renshu.practice

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentPracticeBinding
import com.example.renshu.practice.adapter.CustomListItemAdapter
import com.example.renshu.practice.adapter.SinglePracticeItemAdapter
import com.example.shudomain.list.model.CustomList
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

    private val hiraganaItemAdapter = SinglePracticeItemAdapter { character -> viewModel.openHiragana(character) }
    private val katakanaItemAdapter = SinglePracticeItemAdapter { character -> viewModel.openKatakana(character) }
    private val customListAdapter = CustomListItemAdapter { viewModel.openWordList(it) }

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
        viewModel.customLists.observe(viewLifecycleOwner, ::customListRecyclerView)
    }

    private fun handleNavigationAction(action: PracticeNavigationAction) {
        when (action) {
            is PracticeNavigationAction.OpenHiragana -> openHiraganaCharacter(action.character)
            is PracticeNavigationAction.OpenKatakana -> openKatakanaCharacter(action.character)
            is PracticeNavigationAction.OpenHiraganaPractice -> openKanaExercise(action.alphabet)
            is PracticeNavigationAction.OpenKatakanaPractice -> openKanaExercise(action.alphabet)
            is PracticeNavigationAction.OpenList -> openCustomList(action.listId)
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

    private fun openCustomList(title: String) {
        findNavController().navigate(
            PracticeFragmentDirections
                .actionFragmentPracticeToFragmentListOverview()
                .setTitle(title)
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

    private fun customListRecyclerView(customLists: List<CustomList>) {
        ui.wordListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = customListAdapter
            LinearSnapHelper().attachToRecyclerView(this)
        }

        customListAdapter.submitList(customLists)
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

package com.example.renshu.practice.exercise

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentKanaExerciseBinding
import com.example.renshu.generic.DialogExtension.showQuitExerciseDialog
import com.example.shudomain.exercise.model.AlphabetExercise
import com.example.shupresentation.generic.mvvm.UIState
import com.example.shupresentation.practice.exercise.ExerciseNavigationAction
import com.example.shupresentation.practice.exercise.ExerciseViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class KanaExerciseFragment : DaggerFragment(R.layout.fragment_kana_exercise) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ExerciseViewModel> { viewModelFactory }
    private val ui by viewBinding<FragmentKanaExerciseBinding>()

    private val args by navArgs<KanaExerciseFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ui.collapsingToolbar.title = requireContext().getString(R.string.kana_exercise_title, args.alphabet.capitalize())
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigationAction)
        viewModel.uiState.observe(viewLifecycleOwner, ::handleUIState)
        viewModel.exercise.observe(viewLifecycleOwner, ::initData)
    }

    private fun handleNavigationAction(action: ExerciseNavigationAction) {
        when (action) {
            ExerciseNavigationAction.OpenPractice -> {
                findNavController().popBackStack()
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

    private fun initData(alphabetExercise: AlphabetExercise) {
        initProgressBar(alphabetExercise)
        initBackNavigation(alphabetExercise)

        if (!alphabetExercise.exercisesTodo.isNullOrEmpty()) {
            val currentExercise = alphabetExercise.exercisesTodo[0]

            ui.character.text = currentExercise.exerciseCharacter

            ui.firstChoice.text = currentExercise.exerciseAnswers[0]
            ui.firstChoice.setOnClickListener {
                if (ui.firstChoice.text == currentExercise.correctAnswer) {
                    if (alphabetExercise.exercisesTodo.size == 1) {
                        alphabetExercise.completed = true
                        viewModel.openPracticeFragment(alphabetExercise)
                    }

                    alphabetExercise.exercisesDone.add(currentExercise)
                    alphabetExercise.exercisesTodo.remove(currentExercise)
                } else {
                    alphabetExercise.exercisesTodo.remove(currentExercise)
                    alphabetExercise.exercisesTodo.add(currentExercise)
                }

                initData(alphabetExercise)
            }

            ui.secondChoice.text = currentExercise.exerciseAnswers[1]
            ui.secondChoice.setOnClickListener {
                if (ui.secondChoice.text == currentExercise.correctAnswer) {
                    if (alphabetExercise.exercisesTodo.size == 1) {
                        alphabetExercise.completed = true
                        viewModel.openPracticeFragment(alphabetExercise)
                    }
                    alphabetExercise.exercisesDone.add(currentExercise)
                    alphabetExercise.exercisesTodo.remove(currentExercise)
                } else {
                    alphabetExercise.exercisesTodo.remove(currentExercise)
                    alphabetExercise.exercisesTodo.add(currentExercise)
                }

                initData(alphabetExercise)
            }

            ui.thirdChoice.text = currentExercise.exerciseAnswers[2]
            ui.thirdChoice.setOnClickListener {
                if (ui.thirdChoice.text == currentExercise.correctAnswer) {
                    if (alphabetExercise.exercisesTodo.size == 1) {
                        alphabetExercise.completed = true
                        viewModel.openPracticeFragment(alphabetExercise)
                    }
                    alphabetExercise.exercisesDone.add(currentExercise)
                    alphabetExercise.exercisesTodo.remove(currentExercise)
                } else {
                    alphabetExercise.exercisesTodo.remove(currentExercise)
                    alphabetExercise.exercisesTodo.add(currentExercise)
                }

                initData(alphabetExercise)
            }
        }
    }

    private fun initProgressBar(alphabetExercise: AlphabetExercise) {
        ui.progressBar.max = alphabetExercise.exercisesDone.size + alphabetExercise.exercisesTodo.size
        ui.progressBar.progress = alphabetExercise.exercisesDone.size
    }

    private fun initBackNavigation(alphabetExercise: AlphabetExercise) {
        ui.toolbar.setNavigationOnClickListener {
            requireContext().showQuitExerciseDialog(alphabetExercise) {
                viewModel.openPracticeFragment(alphabetExercise)
            }
        }
    }
}

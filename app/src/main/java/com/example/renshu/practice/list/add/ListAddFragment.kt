package com.example.renshu.practice.list.add

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TableRow
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentAddListBinding
import com.example.shudomain.list.model.CustomListWord
import com.example.shupresentation.practice.list.CustomListNavigationAction
import com.example.shupresentation.practice.list.CustomListViewModel
import com.example.shupresentation.practice.list.validator.CustomListForm
import com.example.shupresentation.practice.list.validator.CustomListFormField
import com.example.shupresentation.practice.list.validator.CustomListFormField.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListAddFragment : DaggerFragment(R.layout.fragment_add_list) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<CustomListViewModel> { viewModelFactory }
    private val ui by viewBinding<FragmentAddListBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        initNavigation()
        initButtons()
    }

    private fun observeViewModel() {
        viewModel.navigation.observe(viewLifecycleOwner, ::handleNavigation)
        viewModel.invalidFields.observe(viewLifecycleOwner, ::handleInvalidFields)
    }

    private fun handleNavigation(action: CustomListNavigationAction) {
        when (action) {
            CustomListNavigationAction.OpenPractice ->
                findNavController().navigate(ListAddFragmentDirections.actionListAddFragmentToPracticeFragment())
        }
    }

    private fun handleInvalidFields(invalidField: CustomListFormField?) {
        when (invalidField) {
            TITLE -> {
                ui.descriptionLayout.error = null
                ui.titleLayout.error = "The title is not set"
            }
            DESCRIPTION -> {
                ui.titleLayout.error = null
                ui.descriptionLayout.error = "The description is not set"
            }
            else -> {
                ui.titleLayout.error = null
                ui.descriptionLayout.error = null
            }
        }
    }

    private fun initNavigation() {
        ui.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun initButtons() {
        ui.createListButton.setOnClickListener { saveList() }
        ui.removeWordButton.setOnClickListener { deleteWordLayout() }
        ui.addWordButton.setOnClickListener { createWordLayout() }
    }

    private fun saveList() {
        val inputEditLayouts = getWords().chunked(3)
        val isValid = checkForEmptyInput(inputEditLayouts)

        if (isValid) {
            val listTitle = ui.titleEdit.text.toString()
            val listDescription = ui.descriptionEdit.text.toString()

            val customWords = arrayListOf<CustomListWord>()
            inputEditLayouts.forEach { inputEditText ->
                customWords.add(
                    CustomListWord(
                        inputEditText[0].text.toString(),
                        inputEditText[1].text.toString(),
                        inputEditText[2].text.toString()
                    )
                )
            }
            viewModel.onCreateListClicked(CustomListForm(listTitle, listDescription), customWords)
        }
    }

    private fun checkForEmptyInput(layoutChunks: List<List<TextInputEditText>>): Boolean {
        var valid = true

        for (chunk in layoutChunks) {
            for (input in chunk) {
                valid = !input.text.isNullOrEmpty()

                if (!valid) {
                    input.error = "ERROR"
                } else {
                    input.error = null
                }
            }
        }

        return valid
    }

    private fun getWords(): ArrayList<TextInputEditText> {
        val wordLayoutChildren = ui.addWordLayout.children
        val customWordList = arrayListOf<TextInputEditText>()

        for (layoutChild in wordLayoutChildren) {
            val wordLayout = layoutChild as LinearLayout
            for (inputLayout in wordLayout.children) {
                val textInputLayout = inputLayout as TextInputLayout
                val frameLayout = textInputLayout[0] as FrameLayout

                for (layout in frameLayout.children) {
                    if (layout is TextInputEditText) {
                        customWordList.add(layout)
                    }
                }
            }
        }

        return customWordList
    }

    private fun deleteWordLayout() {
        val childCount = ui.addWordLayout.childCount
        if (childCount > 1) {
            ui.addWordLayout.removeViewAt(childCount - 1)
        }
    }

    private fun createWordLayout() {
        val childCount = ui.addWordLayout.childCount

        val wordTextInputEditText = TextInputEditText(requireContext())
        wordTextInputEditText.apply {
            id = childCount + 1
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            setPadding(20, 60, 50, 40)
            filters = arrayOf(InputFilter.LengthFilter(15))
        }
        val wordTexInputLayout = TextInputLayout(requireContext())
        wordTexInputLayout.apply {
            layoutParams = TableRow.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            isCounterEnabled = true
            counterMaxLength = 15
            hint = getString(R.string.list_add_hint_word)
            addView(wordTextInputEditText)
        }

        val meaningTextInputEditText = TextInputEditText(requireContext())
        meaningTextInputEditText.apply {
            id = childCount + 2
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            setPadding(20, 60, 50, 40)
            filters = arrayOf(InputFilter.LengthFilter(15))
        }
        val meaningTexInputLayout = TextInputLayout(requireContext())
        meaningTexInputLayout.apply {
            layoutParams = TableRow.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            isCounterEnabled = true
            counterMaxLength = 15
            hint = getString(R.string.list_add_hint_meaning)
            addView(meaningTextInputEditText)
        }

        val kanaTextInputEditText = TextInputEditText(requireContext())
        kanaTextInputEditText.apply {
            id = childCount + 3
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            setPadding(20, 60, 50, 40)
            filters = arrayOf(InputFilter.LengthFilter(15))
        }
        val kanaTexInputLayout = TextInputLayout(requireContext())
        kanaTexInputLayout.apply {
            layoutParams = TableRow.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
            isCounterEnabled = true
            counterMaxLength = 15
            hint = getString(R.string.list_add_hint_kana)
            addView(kanaTextInputEditText)
        }

        val linearLayout = LinearLayout(requireContext())
        linearLayout.apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
            )
            id = childCount + 4
            addView(wordTexInputLayout)
            addView(meaningTexInputLayout)
            addView(kanaTexInputLayout)
        }

        ui.addWordLayout.addView(linearLayout)
    }
}

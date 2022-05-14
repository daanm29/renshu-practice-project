package com.example.renshu.practice.list.add

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.LinearLayout
import android.widget.TableRow
import androidx.core.view.children
import androidx.core.view.marginEnd
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.renshu.R
import com.example.renshu.databinding.FragmentAddListBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.DaggerFragment

class ListAddFragment : DaggerFragment(R.layout.fragment_add_list) {

    private val ui by viewBinding<FragmentAddListBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initNavigation()
        initButtons()
    }

    private fun initNavigation() {
        ui.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }

    private fun initButtons() {
        ui.removeWordButton.setOnClickListener { deleteWordLayout() }
        ui.addWordButton.setOnClickListener { createWordLayout() }
        ui.createListButton.setOnClickListener { /* viewModel.saveList() */ }
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
        }
        val wordTexInputLayout = TextInputLayout(requireContext())
        wordTexInputLayout.apply {
            layoutParams = TableRow.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
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
        }
        val meaningTexInputLayout = TextInputLayout(requireContext())
        meaningTexInputLayout.apply {
            layoutParams = TableRow.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
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
        }
        val kanaTexInputLayout = TextInputLayout(requireContext())
        kanaTexInputLayout.apply {
            layoutParams = TableRow.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f
            )
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

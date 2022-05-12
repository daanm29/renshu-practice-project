package com.example.renshu.generic

import android.content.Context
import androidx.annotation.StyleRes
import com.example.renshu.R
import com.example.shudomain.exercise.model.AlphabetExercise
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogExtension {

    fun Context.showQuitExerciseDialog(alphabetExercise: AlphabetExercise, onConfirm: (AlphabetExercise) -> Unit) {
        showDialog {
            setMessage(R.string.dialog_body)
            setPositiveButton(R.string.dialog_positive) { _, _ -> onConfirm(alphabetExercise)}
            setNegativeButton(R.string.dialog_negative, null)
            setCancelable(false)
        }
    }

    private fun Context.showDialog(@StyleRes style: Int? = null, configuration: MaterialAlertDialogBuilder.() -> Unit) {
        val builder = com.google.android.material.dialog.MaterialAlertDialogBuilder(this, style?: 0)
        configuration(builder)
        builder.show()
    }
}

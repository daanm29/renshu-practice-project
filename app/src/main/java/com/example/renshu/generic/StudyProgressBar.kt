package com.example.renshu.generic

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.renshu.R

class StudyProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    var totalProgress: Int = 0
        set(value) {
            field = value
            val studyTotalBar: ProgressBar = findViewById(R.id.studyTotalBar)
            studyTotalBar.progress = value
        }

    var currentProgress: Int = 0
        set(value) {
            field = value
            val studyCurrentBar: ProgressBar = findViewById(R.id.studyCurrentBar)
            studyCurrentBar.progress = value
        }

    var buttonEnabled: Boolean = false
        set(value) {
            field = value
            val continueButton: Button = findViewById(R.id.continueButton)
            continueButton.isEnabled = value
        }

    var onButtonClicked: (() -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_study_progress_bar, this)

        val studyContinueButton: Button = findViewById(R.id.continueButton)
        studyContinueButton.setOnClickListener { onButtonClicked?.invoke() }
    }
}

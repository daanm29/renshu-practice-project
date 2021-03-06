package com.example.renshu.generic.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.renshu.R

class DualStudyProgressBar @JvmOverloads constructor(
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

    var onButtonClicked: Button? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_dual_study_progress_bar, this)
        onButtonClicked = findViewById(R.id.continueButton)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DualStudyProgressBar)

        val totalTextView: TextView = findViewById(R.id.totalProgressTitle)
        totalTextView.isVisible = attributes.getBoolean(R.styleable.DualStudyProgressBar_enableTotalStreak, true)

        val totalProgressBar: ProgressBar = findViewById(R.id.studyTotalBar)
        totalProgressBar.isVisible = attributes.getBoolean(R.styleable.DualStudyProgressBar_enableTotalStreak, true)

        attributes.recycle()
    }
}

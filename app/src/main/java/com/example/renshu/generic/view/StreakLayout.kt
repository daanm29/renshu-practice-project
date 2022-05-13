package com.example.renshu.generic.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.example.renshu.R

@SuppressLint("Recycle")
class StreakLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    var currentStreak: Int = 0
        set (value) {
            field = value
            val currentStreakView: TextView = findViewById(R.id.currentStreak)
            currentStreakView.text = context.getString(R.string.streak_days, currentStreak.toString())
        }

    var bestStreak: Int = 0
        set (value) {
            field = value
            val bestStreakView: TextView = findViewById(R.id.bestStreak)
            bestStreakView.text = context.getString(R.string.streak_days, bestStreak.toString())
        }

    var lastStudied: Int = 0
        set (value) {
            field = value
            val lastStudiedView: TextView = findViewById(R.id.lastStudied)
            lastStudiedView.text = context.getString(R.string.streak_last_studied, lastStudied.toString())
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_streak_layout, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.StreakLayout)

        val currentStreak: TextView = findViewById(R.id.currentStreak)
        currentStreak.text = context.getString(R.string.streak_days, attributes.getString(R.styleable.StreakLayout_currentStreak))

        val bestStreak: TextView = findViewById(R.id.bestStreak)
        bestStreak.text = context.getString(R.string.streak_days, attributes.getString(R.styleable.StreakLayout_bestStreak))

        val lastStudied: TextView = findViewById(R.id.lastStudied)
        lastStudied.text = context.getString(R.string.streak_last_studied, attributes.getString(R.styleable.StreakLayout_lastStudied))

        attributes.recycle()
    }
}

package com.example.renshu.generic

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.renshu.R

@SuppressLint("Recycle")
class TextButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_text_button, this)
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TextButton)

        val imageView: ImageView = findViewById(R.id.textButtonHeadImage)
        imageView.setImageDrawable(attributes.getDrawable(R.styleable.TextButton_buttonImage))

        val textView: TextView = findViewById(R.id.textButtonTitle)
        textView.text = attributes.getString(R.styleable.TextButton_buttonTitle)

        attributes.recycle()
    }
}

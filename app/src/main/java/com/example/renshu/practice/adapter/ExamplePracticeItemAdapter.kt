package com.example.renshu.practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.renshu.databinding.ItemAlphabetExampleBinding
import com.example.shudomain.practice.model.AlphabetCharacterExample

class ExamplePracticeItemAdapter : ListAdapter<AlphabetCharacterExample, RecyclerView.ViewHolder>(itemDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExamplePracticeItemViewHolder(
            ItemAlphabetExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ExamplePracticeItemViewHolder).bindData(getItem(position))
    }

    inner class ExamplePracticeItemViewHolder(private val ui: ItemAlphabetExampleBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bindData(alphabetExample: AlphabetCharacterExample) {
            ui.japaneseWord.text = alphabetExample.japanese ?: ""
            ui.romajiWord.text = alphabetExample.romaji ?: ""
            ui.meaningWord.text = alphabetExample.meaning ?: ""
        }
    }

    companion object {

        private val itemDiff = object : DiffUtil.ItemCallback<AlphabetCharacterExample>() {
            override fun areItemsTheSame(oldItem: AlphabetCharacterExample, newItem: AlphabetCharacterExample): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: AlphabetCharacterExample, newItem: AlphabetCharacterExample): Boolean {
                return oldItem == newItem
            }
        }
    }
}

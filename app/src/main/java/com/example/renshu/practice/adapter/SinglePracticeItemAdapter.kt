package com.example.renshu.practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.renshu.databinding.ItemAlphabetCharacterBinding
import com.example.renshu.databinding.ItemSmallAlphabetCharacterBinding
import com.example.shudomain.practice.model.AlphabetCharacter

class SinglePracticeItemAdapter(
    private val onClick: (String) -> Unit,
) : ListAdapter<AlphabetCharacter, RecyclerView.ViewHolder>(itemDiff) {

    private var smallItems: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (smallItems) {
            SmallSinglePracticeItemViewHolder(
                ItemSmallAlphabetCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        } else {
            SinglePracticeItemViewHolder(
                ItemAlphabetCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (smallItems) {
            (holder as SmallSinglePracticeItemViewHolder).bindData(getItem(position))
        } else {
            (holder as SinglePracticeItemViewHolder).bindData(getItem(position))
        }
    }

    fun setSmallItems(small: Boolean) {
        this.smallItems = small
    }

    inner class SinglePracticeItemViewHolder(private val ui: ItemAlphabetCharacterBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bindData(alphabetCharacter: AlphabetCharacter) {
            ui.japaneseCharacter.text = alphabetCharacter.japaneseCharacter
            ui.romajiCharacters.text = alphabetCharacter.romajiCharacter
            ui.japaneseCard.setOnClickListener { onClick.invoke(alphabetCharacter.japaneseCharacter) }
        }
    }

    inner class SmallSinglePracticeItemViewHolder(private val ui: ItemSmallAlphabetCharacterBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bindData(alphabetCharacter: AlphabetCharacter) {
            ui.japaneseCharacter.text = alphabetCharacter.japaneseCharacter
            ui.romajiCharacters.text = alphabetCharacter.romajiCharacter
            ui.japaneseCard.setOnClickListener { onClick.invoke(alphabetCharacter.japaneseCharacter) }
        }
    }

    companion object {

        private val itemDiff = object : DiffUtil.ItemCallback<AlphabetCharacter>() {
            override fun areItemsTheSame(oldItem: AlphabetCharacter, newItem: AlphabetCharacter): Boolean {
                return oldItem.japaneseCharacter == newItem.japaneseCharacter
            }

            override fun areContentsTheSame(oldItem: AlphabetCharacter, newItem: AlphabetCharacter): Boolean {
                return oldItem == newItem
            }
        }
    }
}

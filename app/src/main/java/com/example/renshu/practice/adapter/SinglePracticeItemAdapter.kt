package com.example.renshu.practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.renshu.databinding.ItemAlphabetCharacterBinding
import com.example.shudomain.practice.model.AlphabetCharacter

class SinglePracticeItemAdapter(
    private val onClick: (String) -> Unit,
) : ListAdapter<AlphabetCharacter, RecyclerView.ViewHolder>(itemDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SinglePracticeItemViewHolder(
            ItemAlphabetCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SinglePracticeItemViewHolder).bindData(getItem(position))
    }

    inner class SinglePracticeItemViewHolder(val ui: ItemAlphabetCharacterBinding) : RecyclerView.ViewHolder(ui.root) {

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

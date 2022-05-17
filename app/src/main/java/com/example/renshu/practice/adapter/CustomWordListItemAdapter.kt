package com.example.renshu.practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.renshu.databinding.ItemListExampleBinding
import com.example.shudomain.list.model.CustomListWord

class CustomWordListItemAdapter : ListAdapter<CustomListWord, RecyclerView.ViewHolder>(itemDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CustomListWordItemViewHolder(
            ItemListExampleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CustomListWordItemViewHolder).bindData(getItem(position))
    }

    inner class CustomListWordItemViewHolder(private val ui: ItemListExampleBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bindData(customListWord: CustomListWord) {
            ui.japaneseWord.text = customListWord.japanese
            ui.hiraganaWord.text = customListWord.hiragana
            ui.meaningWord.text = customListWord.meaning
        }
    }

    companion object {

        private val itemDiff = object : DiffUtil.ItemCallback<CustomListWord>() {
            override fun areItemsTheSame(oldItem: CustomListWord, newItem: CustomListWord): Boolean {
                return oldItem.japanese == newItem.japanese
            }

            override fun areContentsTheSame(oldItem: CustomListWord, newItem: CustomListWord): Boolean {
                return oldItem == newItem
            }
        }
    }
}

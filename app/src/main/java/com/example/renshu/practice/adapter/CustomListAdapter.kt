package com.example.renshu.practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.renshu.databinding.ItemCustomListBinding
import com.example.shudomain.list.CustomList

class CustomListAdapter(
    private val onClick: (String) -> Unit,
    private val onDelete: (String) -> Unit,
) : ListAdapter<CustomList, RecyclerView.ViewHolder>(itemDiff){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CustomListItemViewHolder(
            ItemCustomListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CustomListItemViewHolder).bindData(getItem(position))
    }

    inner class CustomListItemViewHolder(private val ui: ItemCustomListBinding) : RecyclerView.ViewHolder(ui.root) {

        fun bindData(customList: CustomList) {
            ui.listTitle.text = customList.title
            ui.listSubtitle.text = customList.description
            ui.deleteListIcon.setOnClickListener { onDelete(customList.title) }
            ui.listCardView.setOnClickListener { onClick(customList.title) }
        }
    }

    companion object {

        private val itemDiff = object: DiffUtil.ItemCallback<CustomList>() {
            override fun areItemsTheSame(oldItem: CustomList, newItem: CustomList): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: CustomList, newItem: CustomList): Boolean {
                return oldItem == newItem
            }

        }
    }
}

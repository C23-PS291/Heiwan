package com.c23ps291.heiwan.ui.common.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.c23ps291.heiwan.R
import com.c23ps291.heiwan.data.local.entity.AnimalEntity
import com.c23ps291.heiwan.databinding.ItemAnimalBinding
import com.c23ps291.heiwan.ui.common.adapter.AnimalListAdapter.AnimalViewHolder
import com.c23ps291.heiwan.ui.detail.DetailActivity

class AnimalListAdapter :
    PagingDataAdapter<AnimalEntity, AnimalViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemAnimalBinding =
            ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(itemAnimalBinding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }

    }

    inner class AnimalViewHolder(private val binding: ItemAnimalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: AnimalEntity) {
            binding.apply {
                tvAnimal.text = data.name
                ivAnimal.load(data.image) {
                    crossfade(true)
                    placeholder(R.drawable.placeholder_img)
                    error(R.drawable.placeholder_img)
                }
            }
            itemView.setOnClickListener {

                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AnimalEntity>() {
            override fun areItemsTheSame(oldItem: AnimalEntity, newItem: AnimalEntity): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: AnimalEntity,
                newItem: AnimalEntity,
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
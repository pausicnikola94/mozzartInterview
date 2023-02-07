package com.mozzartinterview.feature_kino_game.ui.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mozzartinterview.databinding.ItemKinoResultsTalonBinding

object KinoResultsTalonComparator : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Int, newItem: Int) =
        oldItem == newItem
}

class KinoResultsTalonAdapter() :
    ListAdapter<Int, KinoResultsTalonViewHolder>(KinoResultsTalonComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        KinoResultsTalonViewHolder(
            ItemKinoResultsTalonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            parent.context
        )

    override fun onBindViewHolder(holder: KinoResultsTalonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
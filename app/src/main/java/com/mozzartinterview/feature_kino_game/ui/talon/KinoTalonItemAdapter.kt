package com.mozzartinterview.feature_kino_game.ui.talon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mozzartinterview.databinding.ItemTalonBinding

object TalonItemComparator : DiffUtil.ItemCallback<KinoTalonItem>() {
    override fun areItemsTheSame(oldItem: KinoTalonItem, newItem: KinoTalonItem) =
        oldItem.number == newItem.number

    override fun areContentsTheSame(oldItem: KinoTalonItem, newItem: KinoTalonItem) =
        oldItem == newItem
}

class TalonItemAdapter(private val clickListener: ((Int) -> Unit)? = null) :
    ListAdapter<KinoTalonItem, KinoTalonItemViewHolder>(TalonItemComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        KinoTalonItemViewHolder(
            ItemTalonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener,
            parent.context
        )

    override fun onBindViewHolder(holder: KinoTalonItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
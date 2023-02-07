package com.mozzartinterview.feature_kino_game.ui.kino_games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mozzartinterview.databinding.ItemKinoGameBinding

object KinoGameItemComparator : DiffUtil.ItemCallback<KinoGameItem>() {
    override fun areItemsTheSame(oldItem: KinoGameItem, newItem: KinoGameItem) =
        oldItem.drawId == newItem.drawId

    override fun areContentsTheSame(oldItem: KinoGameItem, newItem: KinoGameItem) =
        oldItem == newItem
}

class KinoGameAdapter(private val clickListener: ((Int) -> Unit)? = null) :
    ListAdapter<KinoGameItem, KinoGameViewHolder>(KinoGameItemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        KinoGameViewHolder(
            ItemKinoGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener,
            parent.context
        )

    override fun onBindViewHolder(holder: KinoGameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
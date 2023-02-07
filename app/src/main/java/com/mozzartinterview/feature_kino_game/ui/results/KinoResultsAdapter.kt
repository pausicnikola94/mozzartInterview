package com.mozzartinterview.feature_kino_game.ui.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mozzartinterview.databinding.ItemKinoResultsBinding

object KinoResultsComparator : DiffUtil.ItemCallback<KinoResultItem>() {
    override fun areItemsTheSame(oldItem: KinoResultItem, newItem: KinoResultItem) =
        oldItem.drawId == newItem.drawId

    override fun areContentsTheSame(oldItem: KinoResultItem, newItem: KinoResultItem) =
        oldItem == newItem
}

class KinoResultsAdapter(
    private val widthPixels: Int
) :
    ListAdapter<KinoResultItem, KinoResultsViewHolder>(KinoResultsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        KinoResultsViewHolder(
            ItemKinoResultsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            parent.context,
            KinoResultsTalonAdapter(),
            widthPixels
        )

    override fun onBindViewHolder(holder: KinoResultsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
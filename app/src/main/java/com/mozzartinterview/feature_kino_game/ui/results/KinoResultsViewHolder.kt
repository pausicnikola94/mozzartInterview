package com.mozzartinterview.feature_kino_game.ui.results

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.mozzartinterview.core.data.CustomGridLayoutManager
import com.mozzartinterview.databinding.ItemKinoResultsBinding

class KinoResultsViewHolder(
    private val itemBinding: ItemKinoResultsBinding,
    private val context: Context,
    private val kinoResultsTalonAdapter: KinoResultsTalonAdapter?,
    private val widthPixels: Int
) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var _item: KinoResultItem

    fun bind(item: KinoResultItem) {
        itemBinding.resultsTalonItemRV.layoutManager = CustomGridLayoutManager(context, 7, widthPixels)
        itemBinding.resultsTalonItemRV.adapter = kinoResultsTalonAdapter
        kinoResultsTalonAdapter?.submitList(item.winningNumbers.list)
    }
}
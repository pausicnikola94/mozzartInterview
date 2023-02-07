package com.mozzartinterview.feature_kino_game.ui.results

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.mozzartinterview.R
import com.mozzartinterview.databinding.ItemKinoResultsTalonBinding

class KinoResultsTalonViewHolder(
    private val itemBinding: ItemKinoResultsTalonBinding,
    private val context: Context
) : RecyclerView.ViewHolder(itemBinding.root) {

    private var _item: Int? = null

    fun bind(item: Int) {
        _item = item
        itemBinding.numberText.text = _item.toString()
        itemBinding.itemKinoTalonLayout.setBackgroundResource(R.drawable.circle)
    }
}